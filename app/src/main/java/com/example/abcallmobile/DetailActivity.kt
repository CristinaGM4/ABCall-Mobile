package com.example.abcallmobile

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val incident = intent.getSerializableExtra("incident") as? Incidente

        if (incident != null) {
            findViewById<TextView>(R.id.descripcionText).text = "Descripción: ${incident.descripcion}"
            findViewById<TextView>(R.id.estadoText).text = "Estado: ${incident.estado}"
            findViewById<TextView>(R.id.fechaText).text = "Fecha: ${incident.fechaCreacion}"
            findViewById<TextView>(R.id.clienteText).text = "Cliente: ${incident.numDocumentoCliente}"
            findViewById<TextView>(R.id.usuarioText).text = "Usuario: ${incident.numDocumentoUsuario}"
            findViewById<TextView>(R.id.solucionadoText).text = "¿Solucionado?: ${incident.solucionado}"
        }
    }
}
