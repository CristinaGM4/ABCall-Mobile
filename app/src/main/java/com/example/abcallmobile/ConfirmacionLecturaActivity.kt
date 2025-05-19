package com.example.abcallmobile

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ConfirmacionLecturaActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacion_lectura)

        val id = intent.getIntExtra("id", 0)
        val descripcion = intent.getStringExtra("descripcion")
        val estado = intent.getStringExtra("estado")

        val texto = findViewById<TextView>(R.id.textoConfirmacion)
        texto.text = "Incidente #$id\n\nAsunto: $descripcion\nEstado: $estado"

        val btnConfirmar = findViewById<Button>(R.id.btnConfirmarLectura)
        btnConfirmar.setOnClickListener {
            Toast.makeText(this, "Lectura confirmada ✅", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}