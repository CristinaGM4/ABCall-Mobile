package com.example.abcallmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClienteAdapter(
    private val listaClientes: List<Cliente>,
    private val onItemClick: (Cliente) -> Unit
) : RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    class ClienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombreCliente)
        val txtDocumento: TextView = itemView.findViewById(R.id.txtDocumentoCliente)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cliente, parent, false)
        return ClienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val cliente = listaClientes[position]
        holder.txtNombre.text = cliente.socialReason
        holder.txtDocumento.text = "NIT: ${cliente.documentNumber}"
        holder.itemView.setOnClickListener { onItemClick(cliente) }
    }

    override fun getItemCount(): Int = listaClientes.size
}