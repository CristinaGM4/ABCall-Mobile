package com.example.abcallmobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.abcallmobile.IdentificacionUsuarioActivity

class ClientSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_selection)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerClientes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        ApiClient.apiService.listarClientes().enqueue(object : Callback<ClienteResponse> {
            override fun onResponse(call: Call<ClienteResponse>, response: Response<ClienteResponse>) {
                if (response.isSuccessful) {
                    val clientes = response.body()?.data ?: emptyList()
                    Log.d("CLIENTES", "Clientes recibidos: $clientes")

                    val adapter = ClienteAdapter(clientes) { clienteSeleccionado ->
                        val sharedPref = getSharedPreferences("AppPrefs", MODE_PRIVATE)
                        with(sharedPref.edit()) {
                            putString("clienteNombre", clienteSeleccionado.socialReason)
                            putString("clienteDocumento", clienteSeleccionado.documentNumber.toString())
                            apply()
                        }

                        Log.d("CLIENTE", "Guardado clienteNombre=${clienteSeleccionado.socialReason}, clienteDocumento=${clienteSeleccionado.documentNumber}")

                        val intent = Intent(this@ClientSelectionActivity, IdentificacionUsuarioActivity::class.java)
                        intent.putExtra("clienteSeleccionado", clienteSeleccionado.socialReason)
                        startActivity(intent)
                    }

                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(this@ClientSelectionActivity, "Error en respuesta", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ClienteResponse>, t: Throwable) {
                Toast.makeText(this@ClientSelectionActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
                Log.e("CLIENTES", "Error: ${t.message}")
            }
        })
    }
}