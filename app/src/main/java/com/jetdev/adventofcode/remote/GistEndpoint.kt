package com.jetdev.adventofcode.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface GistEndpoint {
    @GET
    fun getContentFromUrl(@Url url: String): Call<String>
}