package com.jetdev.adventofcode.remote

class CodeDownloaderManager(private val endpoint: GistEndpoint) {

    fun getCodeFromUrl(url: String): String {
        val response = endpoint.getContentFromUrl(url).execute()
        return if (response.isSuccessful)
            response.body() ?: ""
        else
            ""
    }
}