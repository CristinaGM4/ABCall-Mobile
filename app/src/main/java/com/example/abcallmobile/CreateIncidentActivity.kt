package com.example.abcallmobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.abcallmobile.ApiClient


class CreateIncidentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_incident)

        val inputAsunto = findViewById<EditText>(R.id.inputAsunto)
        val inputDescripcion = findViewById<EditText>(R.id.inputDescripcion)

        val descripcionChat = intent.getStringExtra("descripcionChatbot")
        if (!descripcionChat.isNullOrEmpty()) {
            inputDescripcion.setText(descripcionChat)
        }

        val btnCrear = findViewById<Button>(R.id.btnCrearIncidente)
        val sharedPref = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val clienteGuardado = sharedPref.getString("clienteNombre", "1010101010")

        btnCrear.setOnClickListener {
            val asunto = inputAsunto.text.toString().trim()
            val descripcion = inputDescripcion.text.toString().trim()

            if (asunto.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val incidenteRequest = mapOf(
                    "tipoDocumentoUsuario" to "CC",
                    "numDocumentoUsuario" to "51287946",
                    "numDocumentoCliente" to "1010101010", // Fijo por ahora
                    "descripcion" to descripcion
                )

                ApiClient.apiService.crearIncidente(incidenteRequest).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@CreateIncidentActivity, "Incidente creado correctamente", Toast.LENGTH_SHORT).show()
                            setResult(RESULT_OK)
                            finish()
                        } else {
                            Toast.makeText(this@CreateIncidentActivity, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(this@CreateIncidentActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }
}
