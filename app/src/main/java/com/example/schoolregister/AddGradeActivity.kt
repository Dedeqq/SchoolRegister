package com.example.schoolregister

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray
import org.json.JSONObject

class AddGradeActivity : AppCompatActivity(){

    var chosenSubject = ""
    var chosenClass = ""
    var chosenStudent = ""
    var chosenGrade = ""
    var chosenWeight = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_grade_activity)

        val subjects = arrayOf("WYBIERZ PRZEDMIOT:", "MATEMATYKA", "JĘZYK POLSKI", "JĘZYK ANGIELSKI")
        val spinnerSubject = findViewById<Spinner>(R.id.spinnerSubject)
        val arrayAdapterSubject = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, subjects)
        spinnerSubject.adapter = arrayAdapterSubject
        spinnerSubject.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                chosenSubject = subjects[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                MainActivity.role = subjects[0]
            }
        }

        val classes = arrayOf("WYBIERZ KLASĘ:", "1A", "1B", "2A", "2B")
        val spinnerClass = findViewById<Spinner>(R.id.spinnerClass)
        val arrayAdapterClass = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classes)
        spinnerClass.adapter = arrayAdapterClass
        spinnerClass.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                chosenClass = classes[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                MainActivity.role = classes[0]
            }
        }

        val students = arrayOf("WYBIERZ UCZNIA:", "UCZEN1_IMIE UCZEN1_NAZWISKO", "UCZEN2_IMIE UCZEN2_NAZWISKO", "UCZEN3_IMIE UCZEN3_NAZWISKO")
        val spinnerStudent = findViewById<Spinner>(R.id.spinnerStudent)
        val arrayAdapterStudent = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, students)
        spinnerStudent.adapter = arrayAdapterStudent
        spinnerStudent.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                chosenStudent = students[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                MainActivity.role = students[0]
            }
        }

        val grades = arrayOf("WYBIERZ OCENĘ:", "5", "4", "3")
        val spinnerGrade = findViewById<Spinner>(R.id.spinnerGrade)
        val arrayAdapterGrade = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, grades)
        spinnerGrade.adapter = arrayAdapterGrade
        spinnerGrade.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                chosenGrade = grades[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                MainActivity.role = classes[0]
            }
        }

        val weights = arrayOf("WYBIERZ WAGĘ OCENY:", "1", "2", "3")
        val spinnerWeight = findViewById<Spinner>(R.id.spinnerWeight)
        val arrayAdapterWeight = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, weights)
        spinnerWeight.adapter = arrayAdapterWeight
        spinnerWeight.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                chosenWeight = weights[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                MainActivity.role = classes[0]
            }
        }
    }

    fun onClickAdd(v: View){
        val description = findViewById<EditText>(R.id.descriptionText).text.toString()
        System.out.println("DODANO OCENE")
        System.out.println(chosenSubject)
        System.out.println(chosenClass)
        System.out.println(chosenStudent)
        System.out.println(chosenGrade)
        System.out.println(chosenWeight)
        System.out.println(description)
        addGrade()


    }

    fun addGrade(){
        val url = "http://10.0.2.2/androiddb/"

        // id grade grade_value weight student_id teacher_id subject_id description
        val query = "INSERT INTO grades (grade, grade_value, weight, student_id, teacher_id, subject_id, description)\t\n" +
                "\tVALUES (\"4+\", 4.5, 2, 2, 2, 2, \"6666\");"



        // Post parameters
        val jsonObject = JSONObject()
        jsonObject.put("username",MainActivity.username)
        jsonObject.put("password",MainActivity.password)
        jsonObject.put("email","email")
        jsonObject.put("query",query)


        // Volley post request with parameters
        val requestPOST = JsonObjectRequest(Request.Method.POST,url,jsonObject,
            Response.Listener { response ->
                // Process the json
                try {
                    Log.d("fun onClickRegister:","Response: $response")
                    startActivity(Intent(this, TeacherMainActivity::class.java))
                }catch (e:Exception){
                    Log.d("fun onClickRegister:","Exception: $e")
                }

            }, Response.ErrorListener{
                // Error in request
                startActivity(Intent(this, TeacherMainActivity::class.java))
                Log.d("fun onClickRegister:","Volley error: $it")
            })
        VolleySingleton.getInstance(this).addToRequestQueue(requestPOST)
    }

}