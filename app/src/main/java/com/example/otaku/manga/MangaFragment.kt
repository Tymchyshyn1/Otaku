package com.example.otaku.manga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.example.otaku.R
import com.example.otaku.databinding.FragmentAnimeBinding
import com.example.otaku.databinding.FragmentMangaBinding


class MangaFragment : Fragment() {
    private var _binding: FragmentMangaBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMangaBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        initWebView()
        return binding.root
    }

    private fun initWebView() = binding.root.apply {
        webChromeClient = WebChromeClient()

        settings.apply {
            setAppCacheEnabled(true)
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            javaScriptEnabled = true
            domStorageEnabled = true
            allowUniversalAccessFromFileURLs = true
            javaScriptCanOpenWindowsAutomatically = false
        }


        loadUrl("https://mangalib.me/")
    }
}