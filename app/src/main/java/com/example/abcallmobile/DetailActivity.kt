package com.example.abcallmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import android.view.View

class DetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val incident = intent.getSerializableExtra("incident") as? Incidente

        if (incident != null) {
            findViewById<TextView>(R.id.descripcionText).text = "Descripción: ${incident.descripcion}"
            findViewById<TextView>(R.id.estadoText).text = "Estado: ${incident.estado}"
            findViewById<TextView>(R.id.fechaText).text = "Fecha: ${incident.fechaCreacion}"
            findViewById<TextView>(R.id.clienteText).text = "Cliente: ${incident.numDocumentoCliente}"
            findViewById<TextView>(R.id.usuarioText).text = "Usuario: ${incident.numDocumentoUsuario}"
        }

        val btnVerSugerencias = findViewById<Button>(R.id.btnVerSugerencias)

        btnVerSugerencias.setOnClickListener {
            if (incident != null) {
                val sugerencias = arrayListOf<String>()
                val descripcion = incident.descripcion.lowercase()

                when {
                    "red" in descripcion -> {
                        sugerencias.add("Paso 1: Verifica que el cable de red esté bien conectado.")
                        sugerencias.add("Paso 2: Reinicia el router y espera 30 segundos.")
                        sugerencias.add("Paso 3: Haz una prueba de conexión desde el navegador.")
                    }

                    "pantalla" in descripcion -> {
                        sugerencias.add("Paso 1: Asegúrate de que la pantalla esté encendida.")
                        sugerencias.add("Paso 2: Revisa el cable HDMI o VGA.")
                        sugerencias.add("Paso 3: Prueba conectando otro monitor.")
                    }

                    "impresora" in descripcion -> {
                        sugerencias.add("Paso 1: Comprueba que la impresora esté encendida.")
                        sugerencias.add("Paso 2: Verifica que haya papel y tinta.")
                        sugerencias.add("Paso 3: Reinstala los drivers si sigue fallando.")
                    }

                    "correo" in descripcion || "email" in descripcion -> {
                        sugerencias.add("Paso 1: Verifica la conexión a internet.")
                        sugerencias.add("Paso 2: Revisa tus credenciales.")
                        sugerencias.add("Paso 3: Pregunta si el servidor de correo está en mantenimiento.")
                    }

                    "audio" in descripcion || "sonido" in descripcion -> {
                        sugerencias.add("Paso 1: Asegúrate de que el volumen esté activado.")
                        sugerencias.add("Paso 2: Conecta audífonos para hacer una prueba.")
                        sugerencias.add("Paso 3: Reinstala los drivers de sonido si es necesario.")
                    }

                    "sistema" in descripcion || "plataforma" in descripcion -> {
                        sugerencias.add("Paso 1: Revisa si el sistema está en mantenimiento.")
                        sugerencias.add("Paso 2: Intenta desde otro navegador o dispositivo.")
                        sugerencias.add("Paso 3: Contacta soporte si persiste.")
                    }

                    "no carga" in descripcion || "cargar la página" in descripcion || "pantalla en blanco" in descripcion -> {
                        sugerencias.add("Paso 1: Refresca la página (Ctrl + F5).")
                        sugerencias.add("Paso 2: Borra caché y cookies del navegador.")
                        sugerencias.add("Paso 3: Verifica tu conexión a internet.")
                    }

                    "inicio de sesión" in descripcion || "login" in descripcion || "no me deja iniciar sesión" in descripcion || "no puedo acceder" in descripcion -> {
                        sugerencias.add("Paso 1: Verifica usuario y contraseña.")
                        sugerencias.add("Paso 2: Asegúrate de no tener bloqueada la cuenta.")
                        sugerencias.add("Paso 3: Intenta restablecer la contraseña.")
                    }

                    "lenta" in descripcion || "página lenta" in descripcion || "falla" in descripcion  || "se queda cargando" in descripcion -> {
                        sugerencias.add("Paso 1: Cierra pestañas o procesos innecesarios.")
                        sugerencias.add("Paso 2: Verifica si otros sitios también están lentos.")
                        sugerencias.add("Paso 3: Reporta el incidente al soporte con hora exacta.")
                    }

                    else -> {
                        sugerencias.add("Paso 1: Reinicia el equipo.")
                        sugerencias.add("Paso 2: Verifica los cables y conexiones.")
                        sugerencias.add("Paso 3: Contacta al soporte técnico si continúa el problema.")
                    }
                }

                // Navega a la pantalla de sugerencias
                val intent = Intent(this, SolucionesActivity::class.java)
                intent.putStringArrayListExtra("sugerencias", sugerencias)
                intent.putExtra("idIncidente", incident.id.toString())
                startActivity(intent)
            }

        }
    }

    override fun onResume() {
        super.onResume()

        val prefs = getSharedPreferences("notificaciones", MODE_PRIVATE)
        val notificarEstado = prefs.getBoolean("notificar_estado", true)

        val rootView = findViewById<View>(android.R.id.content)
        val incident = intent.getSerializableExtra("incident") as? Incidente

        if (notificarEstado && incident != null) {
            val mensaje = "Incidente #${incident.id} – ${incident.descripcion}\nEstado: ${incident.estado}"

            Snackbar.make(rootView, mensaje, Snackbar.LENGTH_INDEFINITE)
                .setAction("VER") {
                    val intent = Intent(this, ConfirmacionLecturaActivity::class.java)
                    intent.putExtra("id", incident.id)
                    intent.putExtra("descripcion", incident.descripcion)
                    intent.putExtra("estado", incident.estado)
                    startActivity(intent)
                }
                .show()
        }
    }
}
