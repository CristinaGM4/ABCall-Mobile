package com.example.abcallmobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IncidentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val incidents = IncidentData.getIncidents()

        adapter = IncidentAdapter(incidents) { incident ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("incident", incident)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
}
