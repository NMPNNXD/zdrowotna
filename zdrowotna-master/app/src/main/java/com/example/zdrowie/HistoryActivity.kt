package com.example.zdrowie

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val sharedPref: SharedPreferences =
            getSharedPreferences("healthPrefs", MODE_PRIVATE)

        val historyText = findViewById<TextView>(R.id.historyText)

        val history = sharedPref.getString("moodHistory", "Brak zapisanych nastrojów")
        historyText.text = history
    }
}
