package com.example.abcallmobile  // Asegúrate de que este paquete sea el correcto

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SolucionesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soluciones)  // Este es el XML que hiciste

        // Conecta el botón por ID
        val btnAceptar = findViewById<Button>(R.id.btnAceptar)

        // Acción cuando el usuario pulsa "Aceptar"
        btnAceptar.setOnClickListener {
            Toast.makeText(this, "Sugerencias aceptadas", Toast.LENGTH_SHORT).show()
            finish()  // Cierra la pantalla
        }
    }
}