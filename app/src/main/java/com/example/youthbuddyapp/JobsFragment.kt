package com.example.youthbuddyapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.youthbuddyapp.databinding.JobsFragmentBinding
import com.example.youthbuddyapp.models.BotInput
import com.example.youthbuddyapp.models.MyText
import com.example.youthbuddyapp.models.QueryInput
import com.example.youthbuddyapp.viewmodels.BotViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking

class JobsFragment : Fragment() {

    private lateinit var binding: JobsFragmentBinding
    private lateinit var viewModel: BotViewModel
    private var employmentDomain = ""
    private var nameOfDomain:String = "medic"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(BotViewModel::class.java)
        binding = JobsFragmentBinding.inflate(inflater, container, false)
        runBlocking{
            Firebase.firestore.collection("users").document(FirebaseAuth.getInstance().currentUser?.uid!!).get().addOnSuccessListener { document ->
                        employmentDomain = document.data?.get("employmentDomain").toString()
                        viewModel.sendData(BotInput(QueryInput(MyText(employmentDomain,"ro-RO"))))
                }
        }
        viewModel.botResponse.observe(viewLifecycleOwner, Observer { botRecommendation ->
            // Handle botRecommendation here (which is non-null)
            nameOfDomain = botRecommendation.queryResult.fulfillmentText
            Log.d("testamm","nameOfDomain ----> $nameOfDomain")
            val webView: WebView = binding.root.findViewById(R.id.webview)
            configureWebView(webView)
            webView.loadUrl("https://ro.indeed.com/jobs?q=${nameOfDomain}&l=Bucure%C8%99ti%2C+Ilfov&vjk=be2b553ee4fb0e87")

            // You can also update your WebView here
        })
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configureWebView(webView: WebView) {
        webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                removeElement(view, "jobsearch-RichSearchBody")
                removeElement(view, "gnav-header-1gheiwz e37uo190")
                removeElement(view, "MobserpJobAlert")
                removeElement(view, "main")
                removeElement(view, "ot-sdk-container")
                binding.progress.visibility = View.GONE
                binding.webview.visibility = View.VISIBLE
            }
        }

        webView.webChromeClient = WebChromeClient()

        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        // ...

        // Adauga stilul CSS pentru a ascunde elementele imediat
        webView.loadDataWithBaseURL(
            null,
            "<style> .jobsearch-RichSearchBody, .gnav-header-1gheiwz, .MobserpJobAlert, .main, .ot-sdk-container { display: none; } </style>",
            "text/html",
            "UTF-8",
            null
        )
    }

    private fun removeElement(webView: WebView?, className: String) {
        webView?.loadUrl("""
            javascript:(function() {
                var element = document.getElementsByClassName('$className')[0];
                if (element) element.style.display = "none";
            })();
        """.trimIndent())
    }
}
