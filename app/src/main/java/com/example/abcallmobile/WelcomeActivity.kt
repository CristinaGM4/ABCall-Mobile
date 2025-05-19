package com.example.abcallmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_welcome)

        val btnEmpezar = findViewById<Button>(R.id.btnEmpezar)
        btnEmpezar.setOnClickListener {
            val intent = Intent(this, ClientSelectionActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}