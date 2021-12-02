package com.jetdev.adventofcode.customview

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import timber.log.Timber

/**
 * NOTE: Because of gradle dependency, it Will use `WebViewCompat` under the hood.
 * - https://developer.android.com/reference/androidx/webkit/WebViewCompat
 * - https://stackoverflow.com/questions/52765057/sample-usage-of-webviewcompat
 * - https://stackoverflow.com/questions/57449900/letting-webview-on-android-work-with-prefers-color-scheme-dark?rq=1
 */
class AppWebViewClient : WebViewClient() {


    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return true // Open any external URL via external browser
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        Timber.d("onPageStarted")
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        Timber.d("onPageFinished")
        super.onPageFinished(view, url)
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        Timber.d("onReceivedError")

        view?.loadUrl("about:blank")
    }
}
fun prismJsHtmlContent(
    formattedSourceCode: String,
    language: String, // Language available via plugin https://prismjs.com/index.html#supported-languages
    showLineNumbers: Boolean = true
): String {
    return """<!DOCTYPE html>
<html>
<head>
    <!-- https://developer.chrome.com/multidevice/webview/pixelperfect -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="www/main.css" rel="stylesheet"/>

    <!-- https://prismjs.com/ -->
    <link href="www/prism.css" rel="stylesheet"/>
    <script src="www/prism.js"></script>
    <!-- HTML/XML encode entities: https://mothereff.in/html-entities -->
</head>
<body>
<pre class="${if (showLineNumbers) "line-numbers" else ""}">
<code class="language-${language}">${formattedSourceCode}</code>
</pre>
</body>
</html>
"""
}