package com.example.abcallmobile

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.abcallmobile.BaseActivity
import android.graphics.Color

class SolucionesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soluciones)

        // 1. Recuperar el ID enviado desde DetailActivity
        val idIncidente = intent.getStringExtra("idIncidente")

// 2. Buscar el TextView y mostrar el ID
        val txtId = findViewById<TextView>(R.id.txtIdIncidente)
        txtId.text = "Incidente: $idIncidente"

        // 1. Recibir la lista de sugerencias
        val sugerencias = intent.getStringArrayListExtra("sugerencias")

        // 2. Buscar el contenedor donde se agregarán los textos
        val contenedor = findViewById<LinearLayout>(R.id.contenedorSugerencias)

        sugerencias?.forEach { texto ->
            val card = LinearLayout(this)
            card.orientation = LinearLayout.HORIZONTAL
            card.setPadding(24, 24, 24, 24)
            card.setBackgroundResource(R.drawable.borde_sugerencia)

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 0, 24)
            card.layoutParams = params

            val puntoVerde = TextView(this)
            puntoVerde.text = "●"
            puntoVerde.setTextColor(Color.parseColor("#4CAF50"))
            puntoVerde.textSize = 18f
            puntoVerde.setPadding(0, 0, 16, 0)

            val textoSugerencia = TextView(this)
            textoSugerencia.text = texto
            textoSugerencia.textSize = 16f

            card.addView(puntoVerde)
            card.addView(textoSugerencia)

            contenedor.addView(card)
        }

        // 4. Acción del botón Aceptar
        val btnAceptar = findViewById<Button>(R.id.btnAceptar)
        btnAceptar.setOnClickListener {
            Toast.makeText(this, "Sugerencias aceptadas", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        mostrarAlertaGeneral("🎓 Recuerda revisar la guía de resolución de incidentes actualizada.")
    }
}
