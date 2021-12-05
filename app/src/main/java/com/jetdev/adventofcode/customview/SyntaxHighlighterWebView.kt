package com.jetdev.adventofcode.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebChromeClient
import androidx.constraintlayout.widget.ConstraintLayout
import com.jetdev.adventofcode.databinding.CustomSyntaxHighlighterLayoutBinding

/**
 * Based on dev.hossain.yaash.example.ui.demoprismjs
 */
class SyntaxHighlighterWebView : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    companion object {
        private const val ANDROID_ASSETS_PATH = "file:///android_asset/"
    }

    private var binding: CustomSyntaxHighlighterLayoutBinding =
        CustomSyntaxHighlighterLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    @SuppressLint("SetJavaScriptEnabled")
    fun bindSyntaxHighlighter(
        formattedSourceCode: String,
        language: String,
        showLineNumbers: Boolean = false
    ) {

        binding.syntaxHighlighterWv.settings.javaScriptEnabled = true
        binding.syntaxHighlighterWv.webChromeClient = WebChromeClient()
        binding.syntaxHighlighterWv.webViewClient = AppWebViewClient {
            binding.syntaxHighlighterLoader.visibility = View.GONE
        }

        binding.syntaxHighlighterWv.loadDataWithBaseURL(
            ANDROID_ASSETS_PATH,
            prismJsHtmlContent(formattedSourceCode, language, showLineNumbers) /* html-data */,
            "text/html",
            "utf-8",
            ""
        )

    }

}