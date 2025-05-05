package com.example.abcallmobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.abcallmobile.ApiClient
import com.example.abcallmobile.IncidentRepository

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var repository: IncidentRepository
    private lateinit var adapter: IncidentAdapter
    private val incidents = mutableListOf<Incidente>()

    private val REQUEST_NUEVO_INCIDENTE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clienteSeleccionado = intent.getStringExtra("clienteSeleccionado")
        if (clienteSeleccionado != null) {
            Toast.makeText(this, "Cliente seleccionado: $clienteSeleccionado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No se recibió ningún cliente", Toast.LENGTH_SHORT).show()
        }

        val sharedPref = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val clienteGuardado = sharedPref.getString("clienteNombre", "Cliente desconocido")

        if (clienteGuardado == "Cliente desconocido") {
            Toast.makeText(this, "No hay cliente guardado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Cliente guardado: $clienteGuardado", Toast.LENGTH_SHORT).show()
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        repository = IncidentRepository()

        adapter = IncidentAdapter(incidents) { incident ->
            try {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("incident", incident)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
        recyclerView.adapter = adapter

        cargarIncidentes() // ← (esto queda, pero falta crearlo)

        val btnFAQ = findViewById<Button>(R.id.btnFAQ)
        btnFAQ.setOnClickListener {
            val intent = Intent(this, FAQActivity::class.java)
            startActivity(intent)
        }

        val btnChatbot = findViewById<FloatingActionButton>(R.id.btnChatbot)
        btnChatbot.setOnClickListener {
            val intent = Intent(this, ChatbotActivity::class.java)
            startActivity(intent)
        }

        val btnNuevo = findViewById<Button>(R.id.btnNuevoIncidente)
        btnNuevo.setOnClickListener {
            val intent = Intent(this, CreateIncidentActivity::class.java)
            startActivityForResult(intent, REQUEST_NUEVO_INCIDENTE)
        }
    }

    private fun cargarIncidentes() {
        lifecycleScope.launch {
            try {
                val nuevosIncidentes = repository.getIncidents()

                val ordenados = nuevosIncidentes.reversed()

                for (incidente in nuevosIncidentes) {
                    Log.d("INCIDENTES_BACKEND", incidente.toString())
                }

                // 🔍 Agrega este log para depuración
                for (incidente in nuevosIncidentes) {
                    println("INCIDENTE → ${incidente}")
                }

                incidents.clear()
                incidents.addAll(nuevosIncidentes)
                adapter.notifyDataSetChanged()
                recyclerView.scrollToPosition(incidents.size - 1)

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_NUEVO_INCIDENTE && resultCode == Activity.RESULT_OK) {
            cargarIncidentes()
            Toast.makeText(this, "Incidente agregado exitosamente", Toast.LENGTH_SHORT).show()
        }
    }
}