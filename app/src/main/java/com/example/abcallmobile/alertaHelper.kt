package com.example.abcallmobile.util

import android.content.Context
import androidx.appcompat.app.AlertDialog

object AlertaHelper {
    fun mostrarAlerta(context: Context, mensaje: String) {
        AlertDialog.Builder(context)
            .setTitle("🔔 Alerta")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar", null)
            .show()
    }
}