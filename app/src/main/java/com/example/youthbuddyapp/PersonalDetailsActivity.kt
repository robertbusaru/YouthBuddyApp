package com.example.youthbuddyapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.youthbuddyapp.databinding.ActivityPersonalDetailsBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso

class PersonalDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalDetailsBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private val storage = Firebase.storage
    private var docUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)

        Picasso.get().load(auth.currentUser?.photoUrl).into(binding.imageView)
        binding.fullNameTextInputLayout.editText?.setText(auth.currentUser?.displayName)
        binding.emailTextInputLayout.editText?.setText(auth.currentUser?.email)
        if (auth.currentUser?.phoneNumber != null) {
            binding.phoneNumberTextInputLayout.editText?.setText(auth.currentUser?.phoneNumber)
        }
        binding.editTextBox.setOnClickListener {
            val fileExplorerIntent = Intent(Intent.ACTION_GET_CONTENT)
            fileExplorerIntent.type = "application/*"
            filePickerActivityResult.launch(fileExplorerIntent)
        }

        binding.saveButton.setOnClickListener {
            val filename = auth.currentUser?.uid.toString()
            val storageReference = FirebaseStorage.getInstance().getReference("documents/$filename")
            if (docUri != null)
                storageReference.putFile(docUri!!).addOnSuccessListener {
                }

            val user = hashMapOf(
                "profilePicture" to auth.currentUser?.photoUrl.toString(),
                "name" to binding.fullNameTextInputLayout.editText?.text.toString(),
                "email" to binding.emailTextInputLayout.editText?.text.toString(),
                "phone" to binding.phoneNumberTextInputLayout.editText?.text.toString(),
            )

            db.collection("users").document(auth.currentUser?.uid.toString())
                .set(user)
                .addOnSuccessListener {
                    val intent = Intent(this, Info1Activity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { e -> Log.d("uploadCheck", e.toString()) }
        }


    }

    private var filePickerActivityResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                binding.uploadCvTextInputLayout.hint = ""
                binding.editTextBox.hint = getFileName(result.data?.data!!)
                docUri = result.data?.data!!

            }
        }

    @SuppressLint("Range", "Recycle")
    fun getFileName(uri: Uri): String {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor = contentResolver.query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } finally {
                cursor!!.close()
            }
        }
        if (result == null) {
            result = uri.path
            val cut = result!!.lastIndexOf('/')
            if (cut != -1) {
                result = result.substring(cut + 1)
            }
        }
        return result
    }
}