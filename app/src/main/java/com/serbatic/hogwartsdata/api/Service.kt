package com.serbatic.hogwartsdata.api

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Service {

    //Get Casas
    fun getCasas(onResult: (String?) -> Unit) {
        val service = ServicioBuilder.buildService()

        CoroutineScope(Dispatchers.IO).launch {
            val response = service?.getHome()

            withContext(Dispatchers.Main) {
                if (response != null) {
                    if (response.isSuccessful) {
                        val gson = GsonBuilder().setPrettyPrinting().create()
                        onResult(gson.toJson(JsonParser.parseString(response.body()?.string())))
                    } else {
                        val gson = GsonBuilder().setPrettyPrinting().create()
                        val result = gson.toJson(JsonParser.parseString(response.errorBody()?.string()))
                        onResult(result)
                    }
                }
            }
        }
    }
    //Get id
    fun getid(id: String, onResult: (String?) -> Unit) {
        val service = ServicioBuilder.buildService()

        CoroutineScope(Dispatchers.IO).launch {
            val response = service?.getid(id)

            withContext(Dispatchers.Main) {
                if (response != null) {
                    if (response.isSuccessful) {
                        val gson = GsonBuilder().setPrettyPrinting().create()
                        onResult(gson.toJson(JsonParser.parseString(response.body()?.string())))
                    } else {
                        val gson = GsonBuilder().setPrettyPrinting().create()
                        val result = gson.toJson(JsonParser.parseString(response.errorBody()?.string()))
                        onResult(result)
                    }
                }
            }
        }
    }
}