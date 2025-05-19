package com.example.abcallmobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListaIncidentesActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IncidentAdapter
    private val incidents = mutableListOf<Incidente>()

    private val REQUEST_NUEVO_INCIDENTE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incident) // ¡Aquí va el XML que tiene el RecyclerView!

        recyclerView = findViewById(R.id.recyclerIncidentes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = IncidentAdapter(incidents) { incident ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("incident", incident)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        cargarIncidentes()

        val btnFAQ = findViewById<Button>(R.id.btnFAQ)
        btnFAQ.setOnClickListener {
            startActivity(Intent(this, FAQActivity::class.java))
        }

        val btnChatbot = findViewById<ImageButton>(R.id.btnChatbot)

        btnChatbot.setOnClickListener {
            startActivity(Intent(this, ChatbotActivity::class.java))
        }

        val btnNuevo = findViewById<Button>(R.id.btnNuevoIncidente)
        btnNuevo.setOnClickListener {
            val intent = Intent(this, CreateIncidentActivity::class.java)
            startActivityForResult(intent, REQUEST_NUEVO_INCIDENTE)
        }
    }

    private fun cargarIncidentes() {
        val prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val tipoDoc = prefs.getString("tipoDocUsuario", "") ?: ""
        val docUsuario = prefs.getString("numeroDocUsuario", "") ?: ""
        val docCliente = prefs.getString("clienteDocumento", "") ?: ""

        Log.d("INCIDENTES", "→ tipoDoc: $tipoDoc | usuario: $docUsuario | cliente: $docCliente")

        val peticion = PeticionConsulta(

              tipoDocUsuario = tipoDoc,
              numeroDocUsuario = docUsuario,
              numeroDocCliente = docCliente
        )

        val gson = com.google.gson.Gson()
        Log.d("PETICION_JSON", gson.toJson(peticion))
        Log.d("INCIDENTES", "→ Enviando petición: $peticion")

        IncidentRepository.getIncidents(peticion).enqueue(object : retrofit2.Callback<IncidenteResponse> {
            override fun onResponse(
                call: retrofit2.Call<IncidenteResponse>,
                response: retrofit2.Response<IncidenteResponse>
            ) {
                Log.d("INCIDENTES", "→ Respuesta HTTP: ${response.code()}")
                val errorJson = response.errorBody()?.string()
                Log.e("INCIDENTES", "⚠️ Respuesta RAW del backend: $errorJson")

                if (response.isSuccessful) {
                    val lista = response.body()?.data?.data
                    Log.d("INCIDENTES", "→ Lista recibida: $lista")

                    val rawJson = response.body()
                    Log.d("INCIDENTES", "🧾 JSON de respuesta: $rawJson")
                    Log.d("INCIDENTES", "➡️ Data cruda: ${response.body()?.data}")
                    Log.d("INCIDENTES", "➡️ Lista final: ${response.body()?.data?.data}")

                    if (lista == null || lista.isEmpty()) {
                        Log.w("INCIDENTES", "→ La lista de incidentes está vacía o nula")
                        Toast.makeText(this@ListaIncidentesActivity, "No se encontraron incidentes", Toast.LENGTH_SHORT).show()
                        return
                    }

                    incidents.clear()
                    incidents.addAll(lista.reversed())
                    adapter.notifyDataSetChanged()
                    recyclerView.scrollToPosition(incidents.size - 1)

                    val incidenteReciente = incidents.first()
                    mostrarNotificacionDeEstado(incidenteReciente)
                    mostrarAlertaGeneral("Hay mantenimiento programado para esta noche a las 10:00 p.m.")

                    if (incidents.isNotEmpty()) {
                        val incidenteReciente = incidents.first()  // el más reciente ya que usas lista.reversed()
                        mostrarNotificacionDeEstado(incidenteReciente)
                        mostrarAlertaGeneral("Hay mantenimiento programado para esta noche a las 10:00 p.m.")
                    }
                } else {
                    Log.e("INCIDENTES", "→ Error ${response.code()}: ${response.errorBody()?.string()}")
                    Toast.makeText(this@ListaIncidentesActivity, "Error consultando incidentes", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<IncidenteResponse>, t: Throwable) {
                Log.e("INCIDENTES", "→ Fallo de red: ${t.message}")
                Toast.makeText(this@ListaIncidentesActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_NUEVO_INCIDENTE && resultCode == Activity.RESULT_OK) {
            cargarIncidentes()
            Toast.makeText(this, "Incidente agregado exitosamente", Toast.LENGTH_SHORT).show()
        }
    }


}