package com.example.abcallmobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IncidentAdapter
    private val incidents = mutableListOf<Incidente>()

    private val REQUEST_NUEVO_INCIDENTE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Cargar datos iniciales
        incidents.addAll(IncidentData.getIncidents())

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

        // Botón para crear nuevo incidente
        val btnNuevo = findViewById<Button>(R.id.btnNuevoIncidente)
        btnNuevo.setOnClickListener {
            val intent = Intent(this, CreateIncidentActivity::class.java)
            startActivityForResult(intent, REQUEST_NUEVO_INCIDENTE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_NUEVO_INCIDENTE && resultCode == Activity.RESULT_OK) {
            val nuevo = data?.getSerializableExtra("nuevoIncidente") as? Incidente
            if (nuevo != null) {
                incidents.add(nuevo)
                adapter.notifyItemInserted(incidents.size - 1)
                Toast.makeText(this, "Incidente agregado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}