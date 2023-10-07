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
import com.google.firebase.storage.ktx.storage

class PersonalDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        val db = Firebase.firestore
        val auth = FirebaseAuth.getInstance()
        Firebase.storage


        binding.editTextBox.setOnClickListener {
            val fileExplorerIntent = Intent(Intent.ACTION_GET_CONTENT)
            fileExplorerIntent.type = "application/*"
            filePickerActivityResult.launch(fileExplorerIntent)
        }
        binding.saveButton.setOnClickListener {
            val user = hashMapOf(
                "name" to binding.fullNameTextInputLayout.editText?.text.toString(),
                "email" to binding.emailTextInputLayout.editText?.text.toString(),
                "phone" to binding.phoneNumberTextInputLayout.editText?.text.toString()
            )
            db.collection("users").document(auth.currentUser?.uid.toString()).set(user).addOnSuccessListener {
            }.addOnFailureListener { e-> Log.d("uploadCheck", e.toString()) }
        }
    }

    private var filePickerActivityResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                binding.editTextBox.hint = getFileName(result.data?.data!!)
                //val stream = FileInputStream(File((result.data?.data!!)).toString())
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