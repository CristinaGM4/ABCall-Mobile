package com.example.abcallmobile

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class FAQActivity : BaseActivity() {

    private val faqMap = mapOf(
        "¿Qué información debe incluir un reporte de incidente?" to "Incluye: nombre del cliente, descripción detallada del incidente y fecha.",
        "¿Dónde puedo ver mis incidentes?" to "Al ingresar a la pantalla principal, aparecerán los incidentes relacionados con cada usuario.",
        "¿Qué debo hacer si un incidente ya fue resuelto por el cliente?" to "Debes marcarlo como 'Solucionado' en la aplicación web e incluir una nota en los comentarios.",
        "¿Puedo editar un incidente después de registrarlo?" to "No. Solo los supervisores pueden editar registros una vez creados.",
        "¿Qué pasa si ingreso datos incorrectos por error?" to "Debes notificar a tu supervisor para que lo corrija.",
        "¿Cómo se priorizan los incidentes?" to "Según urgencia del cliente, impacto en operaciones y tiempo de espera.",
        "¿Qué información es obligatoria al registrar un incidente?" to "Tipo de documento, número del cliente, y descripción del problema.",
        "¿Puedo ver el detalle de un incidente que ya registré?" to "Sí, puedes tocar cualquier incidente en la lista para ver su detalle completo, incluyendo fecha, descripción y estado."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        val listView = findViewById<ListView>(R.id.faq_list)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, faqMap.keys.toList())
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val pregunta = faqMap.keys.toList()[position]
            val respuesta = faqMap[pregunta]

            AlertDialog.Builder(this)
                .setTitle(pregunta)
                .setMessage(respuesta)
                .setPositiveButton("Cerrar", null)
                .show()
        }
    }
}