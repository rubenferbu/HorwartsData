package com.serbatic.hogwartsdata.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface APIService {

    @Headers("Accept: application/json")
    @GET("houses")
    suspend fun getHome(): Response<ResponseBody>

    @Headers("Accept: application/json")
    @GET("houses/{id}")
    suspend fun getid(@Path("id") id: String): Response<ResponseBody>
}