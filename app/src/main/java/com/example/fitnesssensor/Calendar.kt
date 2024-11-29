package com.example.fitnesssensor

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

private val Any.timeInMillis: Any
    get() {
        TODO("Not yet implemented")
    }

class Calendar : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var textViewDate: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarView = findViewById(R.id.calendarView)
        textViewDate = findViewById(R.id.textViewDate)

        // Установка слушателя для изменения даты
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            textViewDate.text = selectedDate

            // Пример получения даты в миллисекундах
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            val dateInMillis = calendar.timeInMillis
            // Выводим дату в миллисекундах (можно использовать по необходимости)
            println("Selected date in milliseconds: $dateInMillis")
        }
    }

    companion object {
        fun getInstance(): Any {
            TODO("Not yet implemented")
        }
    }
}

private fun Any.set(year: Int, month: Int, dayOfMonth: Int) {
    TODO("Not yet implemented")
}
