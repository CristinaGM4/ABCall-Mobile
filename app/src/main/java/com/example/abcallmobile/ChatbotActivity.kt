package com.example.abcallmobile

import android.content.Intent
import android.os.Bundle
import android.widget.*


class ChatbotActivity : BaseActivity() {

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
                intent.putExtra("vieneDelChatbot", true) // 👈 esto es lo que faltaba
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
        val prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val nombreUsuario = prefs.getString("nombreUsuario", null)
        // Si el mensaje contiene la sugerencia de crear incidente, se activa animación
        if (texto.contains("¿Deseas crear un incidente", ignoreCase = true)) {
            val anim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.blink)
            btnCrear.startAnimation(anim)
        }

    }

    private fun responderMensaje(mensaje: String): String {
        val lower = mensaje.lowercase()

        fun contienePalabraEsperada(palabras: List<String>): Boolean {
            return palabras.any { it in lower || esSimilar(lower, it) }
        }

        return when {
            contienePalabraEsperada(listOf("hola", "buenos días", "buenas", "hey", "necesito ayuda")) ->
                "¡Hola! 😊 Soy tu asistente virtual. ¿En qué puedo ayudarte, me puedes decir tu nombre?"

            lower.contains("me llamo") || lower.contains("mi nombre es") || lower.contains("soy") -> {
                val nombre = when {
                    "me llamo" in lower -> lower.substringAfter("me llamo").trim().split(" ")[0]
                    "mi nombre es" in lower -> lower.substringAfter("mi nombre es").trim().split(" ")[0]
                    "soy" in lower -> lower.substringAfter("soy").trim().split(" ")[0]
                    else -> "usuario"
                }.replaceFirstChar { it.uppercase() }

                // Guardar en SharedPreferences
                val prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)
                prefs.edit().putString("nombreUsuario", nombre).apply()

                return "Mucho gusto, $nombre. ¿Podrías darme tu número de documento para validar tu usuario?"
            }

            Regex(".*\\b\\d{6,10}\\b.*").matches(lower) ->
                "Gracias. ¿Cuál es el problema que estás presentando?"

            contienePalabraEsperada(listOf("no carga", "no abre", "página", "bloqueado", "error", "pagina lenta", "el sistema no funciona")) ->
                "Lo siento 😓. Intenta lo siguiente:\n1. Verifica tu conexión a internet.\n2. Cierra y vuelve a abrir la app.\n3. Si el problema persiste, crea un incidente."

            contienePalabraEsperada(listOf("internet", "sin conexión", "wifi", "se cayó")) ->
                "¿Has probado reiniciar tu router? Si el problema continúa, puedes crear un incidente."

            contienePalabraEsperada(listOf("factura", "cuenta", "recibo", "pago")) ->
                "Puedes consultar tus facturas en el portal del cliente. ¿Necesitas el enlace?"

            contienePalabraEsperada(listOf("crear", "incidente", "problema")) ->
                "Puedes usar el botón de abajo para crear un incidente y te ayudaremos lo antes posible."

            contienePalabraEsperada(listOf("gracias", "muchas gracias")) ->
                "¡Con gusto! 😊 ¿Necesitas algo más?"

            contienePalabraEsperada(listOf("no necesito nada más", "todo bien", "ya entendí", "todo claro", "eso era", "listo gracias")) ->
                "¡Perfecto! Me alegra haber ayudado 😊. Estoy aquí si necesitas algo más."

            contienePalabraEsperada(listOf("sí", "tengo otra duda", "otra pregunta", "otra consulta", "más ayuda")) ->
                "Claro, dime qué más necesitas y con gusto te ayudo 🤗."

            contienePalabraEsperada(listOf("perfecto", "excelente", "super", "muy amable", "gracias por todo")) ->
                "¡Gracias a ti por usar ABCall! 🌟 Recuerda que estoy disponible si necesitas algo más."

            contienePalabraEsperada(listOf("gracias otra vez", "gracias de nuevo", "gracias muchas gracias")) ->
                "¡Con mucho gusto! 🤖 Siempre es un placer ayudarte."

            contienePalabraEsperada(listOf("horario", "atención", "cuándo responden", "a qué hora trabajan")) ->
                "Nuestro horario de atención es de lunes a viernes, de 8 a.m. a 6 p.m. ¡Con gusto te ayudaremos dentro de ese horario!"

            contienePalabraEsperada(listOf("adiós", "hasta luego", "chao", "nos vemos")) ->
                "¡Hasta pronto! 👋 No dudes en escribirme si necesitas algo más."

            else -> "Lo siento, no entendí tu mensaje. ¿Deseas crear un incidente con esta descripción?"
        }
    }
}

fun levenshtein(a: String, b: String): Int {
    val dp = Array(a.length + 1) { IntArray(b.length + 1) }
    for (i in a.indices) dp[i + 1][0] = i + 1
    for (j in b.indices) dp[0][j + 1] = j + 1

    for (i in a.indices) {
        for (j in b.indices) {
            val cost = if (a[i] == b[j]) 0 else 1
            dp[i + 1][j + 1] = minOf(
                dp[i][j + 1] + 1,
                dp[i + 1][j] + 1,
                dp[i][j] + cost
            )
        }
    }
    return dp[a.length][b.length]
}

fun esSimilar(a: String, b: String, umbral: Int = 2): Boolean {
    return levenshtein(a.lowercase(), b.lowercase()) <= umbral
}