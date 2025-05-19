package com.example.abcallmobile

import retrofit2.Call

object IncidentRepository {

    fun getIncidents(peticion: PeticionConsulta): Call<IncidenteResponse> {
        return ApiClient.apiService.consultarIncidentes(peticion)
    }
}