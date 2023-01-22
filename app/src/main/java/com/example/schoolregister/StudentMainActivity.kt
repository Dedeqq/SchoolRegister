package com.example.schoolregister

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudentMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_activity_main)
        val greetings: TextView = findViewById(R.id.greetings)
        greetings.setText("WITAJ "+MainActivity.firstName+" "+MainActivity.lastName+"!")
    }

    fun onClickB(v: View) {
        System.out.println("PRINT LAST NAME")
        System.out.println(MainActivity.lastName)
        System.out.println("PRINT FIRST NAME")
        System.out.println(MainActivity.firstName)
    }

}