package com.example.abcallmobile

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log


class IdentificacionUsuarioActivity : AppCompatActivity() {

    private lateinit var spinnerTipoDoc: Spinner
    private lateinit var edtNumeroDoc: EditText
    private lateinit var txtCliente: TextView
    private lateinit var btnContinuar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identificacion_usuario)

        spinnerTipoDoc = findViewById(R.id.spinnerTipoDoc)
        edtNumeroDoc = findViewById(R.id.edtNumeroDocumento)
        txtCliente = findViewById(R.id.txtClienteSeleccionado)
        btnContinuar = findViewById(R.id.btnContinuar)

        // 1. Obtener cliente desde SharedPreferences
        val prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val clienteNombre = prefs.getString("clienteNombre", "")
        val clienteDocumento = prefs.getString("clienteDocumento", "")
        val numeroCliente = clienteDocumento ?: ""

        txtCliente.text = "Cliente: $clienteNombre"

        // 2. Cargar opciones del spinner
        val opciones = listOf("1", "2", "3", "4", "5", "6")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)
        spinnerTipoDoc.adapter = adapter

        // 3. Al presionar el botón
        btnContinuar.setOnClickListener {
            val tipoDoc = spinnerTipoDoc.selectedItem.toString()
            val numeroDocUsuario = edtNumeroDoc.text.toString()


            if (numeroDocUsuario.isBlank()) {
                Toast.makeText(this, "Ingresa el número de documento del usuario", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 4. Guardar en SharedPreferences
            with(prefs.edit()) {
                putString("tipoDocUsuario", tipoDoc)
                putString("numeroDocUsuario", numeroDocUsuario)
                putString("clienteDocumento", numeroCliente)
                apply()
            }

            Log.d("IDENTIFICACION", "Guardado → tipo: $tipoDoc, usuario: $numeroDocUsuario, cliente: $clienteDocumento")

            // 5. Redirigir a lista de incidentes
            val intent = Intent(this, ListaIncidentesActivity::class.java)
            startActivity(intent)
        }
    }
}