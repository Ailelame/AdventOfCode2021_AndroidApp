package com.jetdev.adventofcode.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebChromeClient
import android.webkit.WebView

/**
 * Based on dev.hossain.yaash.example.ui.demoprismjs
 */
class SyntaxHighlighterWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    companion object {
        private const val ANDROID_ASSETS_PATH = "file:///android_asset/"
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun bindSyntaxHighlighter(
        formattedSourceCode: String,
        language: String,
        showLineNumbers: Boolean = false
    ) {
        settings.javaScriptEnabled = true
        webChromeClient = WebChromeClient()
        webViewClient = AppWebViewClient()

        loadDataWithBaseURL(
            ANDROID_ASSETS_PATH ,
            prismJsHtmlContent(formattedSourceCode, language, showLineNumbers) /* html-data */,
            "text/html" ,
            "utf-8",
            ""
        )
    }
}