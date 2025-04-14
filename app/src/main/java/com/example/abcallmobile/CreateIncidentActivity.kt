package com.example.abcallmobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateIncidentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_incident)

        val inputAsunto = findViewById<EditText>(R.id.inputAsunto)
        val inputDescripcion = findViewById<EditText>(R.id.inputDescripcion)
        val btnCrear = findViewById<Button>(R.id.btnCrearIncidente)

        btnCrear.setOnClickListener {
            val asunto = inputAsunto.text.toString().trim()
            val descripcion = inputDescripcion.text.toString().trim()

            if (asunto.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {

                val nuevoIncidente = com.example.abcallmobile.Incidente(
                    id = 0,
                    titulo = asunto,
                    descripcion = descripcion,
                    estado = "Registrado",
                    fecha = "2025-04-13",
                    empresa = "N/A",
                    cliente = "N/A",
                    gestion = "Pendiente"
                )

                val resultIntent = intent
                resultIntent.putExtra("nuevoIncidente", nuevoIncidente)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
