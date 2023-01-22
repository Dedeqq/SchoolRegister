package com.example.schoolregister

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TeacherMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_activity_main)
        val greetings: TextView = findViewById(R.id.greetings)
        greetings.setText("WITAJ "+MainActivity.firstName+" "+MainActivity.lastName+"!")
    }


}