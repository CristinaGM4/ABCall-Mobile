package com.example.abcallmobile

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ChatbotActivity : AppCompatActivity() {

    private lateinit var inputMensaje: EditText
    private lateinit var chatLog: TextView
    private lateinit var btnCrear: Button
    private var ultimoMensajeUsuario: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbot)

        inputMensaje = findViewById(R.id.inputMensaje)
        chatLog = findViewById(R.id.chatLog)
        btnCrear = findViewById(R.id.btnCrearIncidente)

        val btnEnviar = findViewById<ImageButton>(R.id.btnEnviar)
        btnEnviar.setOnClickListener {
            val mensaje = inputMensaje.text.toString().trim()
            if (mensaje.isNotEmpty()) {
                ultimoMensajeUsuario = mensaje
                agregarMensaje("🔵 Tú: $mensaje")
                val respuesta = responderMensaje(mensaje)
                agregarMensaje("🟢 Chatbot: $respuesta")
                inputMensaje.text.clear()
            }
        }


        btnCrear.setOnClickListener {
            if (ultimoMensajeUsuario.isNotEmpty()) {
                val intent = Intent(this, CreateIncidentActivity::class.java)
                intent.putExtra("descripcionChatbot", ultimoMensajeUsuario)
                startActivity(intent)

                // 🧹 Limpiar chat e input
                chatLog.text = "🟢 Chatbot: ¡Hola! ¿En qué puedo ayudarte hoy?"
                inputMensaje.text.clear()

                // ✅ Confirmación visual
                Toast.makeText(this, "Creando incidente con la última descripción", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Escribe un mensaje primero", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun agregarMensaje(texto: String) {
        chatLog.append("\n\n$texto")
    }

    private fun responderMensaje(mensaje: String): String {
        return when {
            mensaje.contains("hola", ignoreCase = true) ||
                    mensaje.contains("buenos días", ignoreCase = true) ||
                    mensaje.contains("buenas", ignoreCase = true) ->
                "¡Hola! 😊 Soy tu asistente virtual. ¿Cuál es tu nombre?"

            mensaje.contains("me llamo", ignoreCase = true) ||
                    mensaje.contains("soy", ignoreCase = true) ->
                "Mucho gusto. ¿Podrías darme tu número de documento para validar tu usuario?"

            mensaje.contains("51287946") || mensaje.matches(Regex(".*\\d{6,10}.*")) ->
                "Gracias. ¿En qué puedo ayudarte hoy?"

            mensaje.contains("no carga", ignoreCase = true) ||
                    mensaje.contains("no abre", ignoreCase = true) ||
                    mensaje.contains("página", ignoreCase = true) ->
                "Lo siento 😓. Intenta lo siguiente:\n1. Verifica tu conexión a internet.\n2. Intenta cerrar y abrir la app.\n3. Si el problema persiste, crea un incidente."

            mensaje.contains("incidente", ignoreCase = true) ||
                    mensaje.contains("crear", ignoreCase = true) ->
                "Puedes usar el botón de abajo para crear un incidente y te ayudaremos lo antes posible."

            mensaje.contains("gracias", ignoreCase = true) ->
                "¡Con gusto! 😊 ¿Necesitas algo más?"

            mensaje.contains("adiós", ignoreCase = true) ||
                    mensaje.contains("hasta luego", ignoreCase = true) ->
                "¡Hasta pronto! 👋 No dudes en escribirme si necesitas algo más."

            else -> "Mmm... aún estoy aprendiendo. ¿Podrías decirlo de otra forma?"
        }
    }
}