package com.example.abcallmobile

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val incident = intent.getSerializableExtra("incident") as Incidente

        findViewById<TextView>(R.id.titleText).text = incident.titulo
        findViewById<TextView>(R.id.estadoText).text = "Estado: ${incident.estado}"
        findViewById<TextView>(R.id.fechaText).text = "Fecha: ${incident.fecha}"
        findViewById<TextView>(R.id.empresaText).text = "Empresa: ${incident.empresa}"
        findViewById<TextView>(R.id.clienteText).text = "Cliente: ${incident.cliente}"
        findViewById<TextView>(R.id.descripcionText).text = incident.descripcion
        findViewById<TextView>(R.id.gestionText).text = "Gestión: ${incident.gestion}"
    }
}
