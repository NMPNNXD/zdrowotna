package com.example.zdrowie

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.content.Intent
import android.content.SharedPreferences
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var counter = 0
    private lateinit var sharedPref: SharedPreferences

    private val tips = listOf(
        "Sen jest kluczowy dla regeneracji.",
        "Sen jest kluczowy dla regeneracji.",
        "Sen jest kluczowy dla regeneracji.",
        "Oglądaj seriale lub filmy przez całą noc zamiast spać.",
        "Sen jest kluczowy dla regeneracji.",
        "Pij wodę.",
        "Pij wodę.",
        "Czuj się dobrze to nie będziesz mieć depresji.",
        "Hazard poprawi ci chumor.",
        "Nikt i tak tego nie czyta...",
        "Pij wodę...albo i nie, twój wybór...",
        "Idź już śpać!",
        "Czuj się lepiej!",
        "Gdy czujesz się gorzej zapomnij o problemach.",
        "Tobie i tak nic już nie pomoże.",
        "Pij osiem szklanek wody dziennie.",
        "Pij osiem szklanek wody dziennie.",
        "Pij osiem szklanek wody dziennie.",
        "Pij osiem szklanek wody dziennie.",
        "Śpij conajmniej 8 godzin dziennie.",
        "Śpij conajmniej 8 godzin dziennie.",
        "Śpij conajmniej 8 godzin dziennie.",
        "Śpij ile uważasz żę potrzebujesz.",
        "Śpij conajmniej 8 godzin dziennie.",
        "Odżywiaj się zdrowo.",
        "Odżywiaj się zdrowo.",
        "Jedz co jadalne.",
        "Odżywiaj się zdrowo.",
        "Odżywiaj się zdrowo.",
        "Nie kozystaj z telefonu przed pójściem spać.",
        "Nie kozystaj z telefonu przed pójściem spać.",
        "Nie kozystaj z telefonu przed pójściem spać.",
        "Nie kozystaj z telefonu przed pójściem spać.",
        "Idź spać o normalnej porze.",
        "Idź spać o normalnej porze.",
        "Idź spać o normalnej porze.",
        "Idź spać o normalnej porze.",
        "Sen jest przereklamowany.",
        "Ortografia jest trudna",
        "Następna wskazówka kłamie!",
        "ERROR 404",
        "\uD83D\uDE36",
        "\uD83D\uDE11",
        "\uD83D\uDDFF",



    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = getSharedPreferences("healthPrefs", MODE_PRIVATE)

        val dateText = findViewById<TextView>(R.id.dateText)
        val glassCounter = findViewById<TextView>(R.id.glassCounter)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val addButton = findViewById<Button>(R.id.addButton)
        val removeButton = findViewById<Button>(R.id.removeButton)
        val resetButton = findViewById<Button>(R.id.resetButton)

        val loveBtn = findViewById<Button>(R.id.loveBtn)
        val happyBtn = findViewById<Button>(R.id.happyBtn)
        val neutralBtn = findViewById<Button>(R.id.neutralBtn)
        val sadBtn = findViewById<Button>(R.id.sadBtn)
        val angryBtn = findViewById<Button>(R.id.angryBtn)
        val historyButton = findViewById<Button>(R.id.historyButton)

        val tipText = findViewById<TextView>(R.id.tipText)
        val newTipButton = findViewById<Button>(R.id.newTipButton)


        val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        dateText.text = "Dzisiaj: ${sdf.format(Date())}"


        counter = sharedPref.getInt("counter", 0)
        updateUI(glassCounter, progressBar)

        addButton.setOnClickListener {
            if (counter < 8) {
                counter++
                updateUI(glassCounter, progressBar)
            }
        }

        removeButton.setOnClickListener {
            if (counter > 0) {
                counter--
                updateUI(glassCounter, progressBar)
            }
        }

        resetButton.setOnClickListener {
            counter = 0
            updateUI(glassCounter, progressBar)
        }


        fun saveMood(mood: String) {
            val currentDate = sdf.format(Date())
            val history = sharedPref.getString("moodHistory", "") ?: ""
            val newEntry = "$currentDate - $mood\n"
            sharedPref.edit().putString("moodHistory", history + newEntry).apply()
        }

        loveBtn.setOnClickListener { saveMood("😍 YIPPEE") }
        happyBtn.setOnClickListener { saveMood("😊 Nepopil, neporuhal, a veselý") }
        neutralBtn.setOnClickListener { saveMood("😐 Neutralny") }
        sadBtn.setOnClickListener { saveMood("😢 Popil, poruhál, a smutny") }
        angryBtn.setOnClickListener { saveMood("😡 Skończyło się babci sranie") }

        historyButton.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }


        tipText.text = tips.random()

        newTipButton.setOnClickListener {
            tipText.text = tips.random()
        }
    }

    private fun updateUI(counterText: TextView, progressBar: ProgressBar) {
        counterText.text = "$counter/8"
        progressBar.progress = counter
    }

    override fun onPause() {
        super.onPause()
        sharedPref.edit().putInt("counter", counter).apply()
    }
}
