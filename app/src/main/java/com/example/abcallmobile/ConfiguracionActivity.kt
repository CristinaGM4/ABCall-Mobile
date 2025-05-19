package com.example.abcallmobile

import android.content.Context
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast

class ConfiguracionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)

        // Acceso a preferencias
        val prefs = getSharedPreferences("notificaciones", Context.MODE_PRIVATE)

        val switchEstado = findViewById<Switch>(R.id.switchEstado)
        val switchAlertas = findViewById<Switch>(R.id.switchAlertas)

        switchEstado.isChecked = prefs.getBoolean("notificar_estado", true)
        switchAlertas.isChecked = prefs.getBoolean("notificar_alertas", true)

        switchEstado.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("notificar_estado", isChecked).apply()
            val msg = if (isChecked) "Recibirás notificaciones de cambios de estado" else "Ya no recibirás cambios de estado"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        switchAlertas.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("notificar_alertas", isChecked).apply()
            val msg = if (isChecked) "Recibirás alertas informativas" else "Has desactivado las alertas informativas"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}
