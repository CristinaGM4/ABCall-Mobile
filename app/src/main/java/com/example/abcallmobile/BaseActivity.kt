package com.example.abcallmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import android.os.Handler
import android.os.Looper

open class BaseActivity : AppCompatActivity() {

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Proteger por si no hay barra
        val btnConfig = findViewById<ImageButton?>(R.id.btnConfiguracion)
        val btnSalir = findViewById<ImageButton?>(R.id.btnSalir)
        val btnChatbot = findViewById<ImageButton?>(R.id.btnChatbot)

        btnConfig?.setOnClickListener {
            if (this !is ConfiguracionActivity) {
                startActivity(Intent(this, ConfiguracionActivity::class.java))
            }
        }

        btnSalir?.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        btnChatbot?.setOnClickListener {
            startActivity(Intent(this, ChatbotActivity::class.java))
        }
    }

    fun mostrarNotificacionDeEstado(incident: Incidente) {
        val prefs = getSharedPreferences("notificaciones", MODE_PRIVATE)
        val notificarEstado = prefs.getBoolean("notificar_estado", true)

        if (!notificarEstado) return

        val mensaje = "Incidente #${incident.id} – ${incident.descripcion}\nEstado: ${incident.estado}"
        val rootView = findViewById<View>(android.R.id.content)

        // Primer Snackbar con VER
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG)
            .setAction("VER") {
                val intent = Intent(this, ConfirmacionLecturaActivity::class.java)
                intent.putExtra("id", incident.id)
                intent.putExtra("descripcion", incident.descripcion)
                intent.putExtra("estado", incident.estado)
                startActivity(intent)
            }
            .show()

        // Segundo Snackbar para permitir cerrar explícitamente
        Handler(Looper.getMainLooper()).postDelayed({
            Snackbar.make(rootView, "¿Deseas descartar esta notificación?", Snackbar.LENGTH_SHORT)
                .setAction("CERRAR") {}  // solo cierra
                .show()
        }, 3500) // después de que el primero desaparezca
    }

    private var alertaMostradaEstaSesion = false

    fun mostrarAlertaGeneral(mensaje: String) {
        val prefs = getSharedPreferences("notificaciones", MODE_PRIVATE)
        val notificarAlertas = prefs.getBoolean("notificar_alertas", true)

        if (!notificarAlertas || alertaMostradaEstaSesion) return

        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, "📢 $mensaje", Snackbar.LENGTH_LONG)
            .setAction("Cerrar") {}
            .show()

        alertaMostradaEstaSesion = true
    }

}