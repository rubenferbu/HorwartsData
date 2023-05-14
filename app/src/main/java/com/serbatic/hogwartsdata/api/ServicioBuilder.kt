package com.serbatic.hogwartsdata.api

import retrofit2.Retrofit

object ServicioBuilder {

    private val retrofit = Retrofit.Builder().baseUrl("https://wizard-world-api.herokuapp.com/").build()
    private val service = retrofit.create(APIService::class.java)

    fun buildService(): APIService? {
        return service
    }

}