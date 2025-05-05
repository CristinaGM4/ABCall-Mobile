package com.example.abcallmobile

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @POST("incidentes/v1/crear")
    fun crearIncidente(@Body body: Map<String, String>): Call<Void>

    @GET("incidentes/v1/consultar")
    suspend fun getIncidents(
        @Query("tipoDocUsuario") tipoDoc: String,
        @Query("numeroDocUsuario") numeroDoc: String
    ): IncidentesResponse
}


