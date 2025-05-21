package com.example.abcallmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateIncidentActivity : BaseActivity() {
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

        // Obtener datos guardados del usuario y cliente
        val sharedPref = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val tipoDocUsuario = sharedPref.getString("tipoDocUsuario", "1") ?: "1"
        val numeroDocUsuario = sharedPref.getString("numeroDocUsuario", "") ?: ""
        val numeroDocCliente = sharedPref.getString("clienteDocumento", "") ?: ""

        btnCrear.setOnClickListener {
            val asunto = inputAsunto.text.toString().trim()
            val descripcion = inputDescripcion.text.toString().trim()

            if (asunto.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val incidenteRequest = mapOf(
                    "tipoDocumentoUsuario" to tipoDocUsuario,
                    "numDocumentoUsuario" to numeroDocUsuario,
                    "numDocumentoCliente" to numeroDocCliente,
                    "descripcion" to descripcion
                )

                // 🟡 NUEVA LLAMADA CON MODELO DE RESPUESTA
                ApiClient.apiService.crearIncidente(incidenteRequest)
                    .enqueue(object : Callback<CrearIncidenteResponse> {
                        override fun onResponse(
                            call: Call<CrearIncidenteResponse>,
                            response: Response<CrearIncidenteResponse>
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                val raw = response.body()!!.data

                                // Convertimos de IncidenteRaw a tu modelo Incidente
                                val incidente = Incidente(
                                    id = 0, // backend no lo envía por ahora
                                    tipoDocumentoUsuario = raw.tipoDocumentoUsuario.toInt(),
                                    numDocumentoUsuario = raw.numDocumentoUsuario,
                                    numDocumentoCliente = raw.numDocumentoCliente.toLong(),
                                    descripcion = raw.descripcion,
                                    estado = raw.status,
                                    fechaCreacion = "" // puedes poner fecha actual o dejarlo vacío
                                )

                                Toast.makeText(this@CreateIncidentActivity, "Incidente creado correctamente", Toast.LENGTH_SHORT).show()

                                val vieneDelChatbot = intent.getBooleanExtra("vieneDelChatbot", false)

                                if (vieneDelChatbot) {
                                    // Ir a ListadoIncidentesActivity directamente
                                    val intent = Intent(this@CreateIncidentActivity, ListaIncidentesActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                                    startActivity(intent)
                                    finish()
                                } else {
                                    val resultIntent = Intent()
                                    resultIntent.putExtra("nuevoIncidente", incidente)
                                    setResult(RESULT_OK, resultIntent)
                                    finish()
                                }
                            } else {
                                Toast.makeText(this@CreateIncidentActivity, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<CrearIncidenteResponse>, t: Throwable) {
                            Toast.makeText(this@CreateIncidentActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                        }
                    })
            }
        }
    }
}
