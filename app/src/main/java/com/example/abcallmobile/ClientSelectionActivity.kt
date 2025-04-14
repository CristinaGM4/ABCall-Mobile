package com.example.abcallmobile

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class ClientSelectionActivity : AppCompatActivity() {

    private val clientes = arrayOf(
        "Cliente 1", "Cliente 2", "Cliente 3",
        "Cliente 4", "Cliente 5", "Cliente 6"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_selection)

        val gridView = findViewById<GridView>(R.id.gridClientes)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, clientes)
        gridView.adapter = adapter

        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, MainActivity::class.java) // ← Cambia si tu vista principal tiene otro nombre
            intent.putExtra("clienteSeleccionado", clientes[position])
            startActivity(intent)
        }
    }
}