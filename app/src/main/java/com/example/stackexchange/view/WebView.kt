package com.example.stackexchange.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stackexchange.databinding.FragmentWebViewBinding


class WebView : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding= FragmentWebViewBinding.inflate(inflater, container, false)
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.loadUrl(getArguments()?.getString("url").toString());
        return binding.root

    }


}