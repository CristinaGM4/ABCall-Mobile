package com.example.abcallmobile

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IncidentRepository {
    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://abcall-gateway-bwh34xmh.uc.gateway.dev/service/abcall/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getIncidents(): List<Incidente> {
        val tipoDoc = "CC"
        val numeroDoc = "51287946"
        return apiService.getIncidents(tipoDoc, numeroDoc).data
    }


}