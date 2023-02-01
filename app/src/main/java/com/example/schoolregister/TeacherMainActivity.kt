package com.example.schoolregister

import android.content.Intent
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

    fun onClickB(v: View) {
       startActivity(Intent(Intent(this, AddGradeActivity::class.java) ))
    }

    fun onClickTeacherTests(v: View){
        startActivity(Intent(this, TeacherTestsActivity::class.java))
    }

    fun onClickLogout(v: View) {
        MainActivity.gradesPolish.clear()
        MainActivity.gradesEnglish.clear()
        MainActivity.gradesMathematics.clear()
        MainActivity.firstName = ""
        MainActivity.lastName = ""
        MainActivity.studentFirstName = ""
        MainActivity.studentLastName = ""
        MainActivity.username = ""
        MainActivity.password = ""
        MainActivity.session = ""
        MainActivity.role = ""
        MainActivity.loggedin = false

        startActivity(Intent(this, MainActivity::class.java))
    }

}