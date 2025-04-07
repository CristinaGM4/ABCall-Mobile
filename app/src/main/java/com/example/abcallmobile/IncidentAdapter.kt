package com.example.abcallmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IncidentAdapter(
    private val incidents: List<Incidente>,
    private val onClick: (Incidente) -> Unit
) : RecyclerView.Adapter<IncidentAdapter.IncidentViewHolder>() {

    inner class IncidentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.itemTitle)
        val estado: TextView = view.findViewById(R.id.itemEstado)
        init {
            view.setOnClickListener {
                onClick(incidents[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_incident, parent, false)
        return IncidentViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
        val incident = incidents[position]
        holder.title.text = incident.titulo
        holder.estado.text = incident.estado
    }

    override fun getItemCount(): Int = incidents.size
}
