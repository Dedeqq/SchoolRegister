package com.example.schoolregister

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GradesActivity : AppCompatActivity() {

    private lateinit var customAdapter1: CustomAdapter
    private lateinit var customAdapter2: CustomAdapter
    private lateinit var customAdapter3: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grades_activity_main)
        val studentGrades: TextView = findViewById(R.id.studentGrades)
        studentGrades.setText(MainActivity.studentFirstName+" "+MainActivity.studentLastName)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView1)
        customAdapter1 = CustomAdapter(MainActivity.gradesMathematics)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter1
        customAdapter1.notifyDataSetChanged()


        val recyclerView2: RecyclerView = findViewById(R.id.recyclerView2)
        customAdapter2 = CustomAdapter(MainActivity.gradesPolish)
        val layoutManager2 = LinearLayoutManager(applicationContext)
        recyclerView2.layoutManager = layoutManager2
        recyclerView2.adapter = customAdapter2
        customAdapter2.notifyDataSetChanged()

        val recyclerView3: RecyclerView = findViewById(R.id.recyclerView3)
        customAdapter3 = CustomAdapter(MainActivity.gradesEnglish)
        val layoutManager3 = LinearLayoutManager(applicationContext)
        recyclerView3.layoutManager = layoutManager3
        recyclerView3.adapter = customAdapter3
        customAdapter2.notifyDataSetChanged()

    }

    fun onClickBack(v: View) {
        when (MainActivity.role) {
            "Uczeń" -> intent = Intent(this, StudentMainActivity::class.java)
            "Nauczyciel" -> intent = Intent(this, TeacherMainActivity::class.java)
            "Rodzic" -> intent = Intent(this, ParentMainActivity::class.java)
            "Inna" -> intent = Intent(this, OtherMainActivity::class.java)
        }
        startActivity(intent)
    }

}