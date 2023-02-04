package com.example.schoolregister

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.schoolregister.TeacherTest
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

val url = "http://10.0.2.2/androiddb/"

class TeacherTestsActivity : AppCompatActivity(),
    TeacherTestsAdapter.UsunZadanieListener {

    private val tests = mutableListOf<TeacherTest>()
    private lateinit var adapter : TeacherTestsAdapter
    var schoolClass = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_tests_activity_main)

        val classes = arrayOf("WYBIERZ KLASĘ:", "1A", "1B")
        val spinnerClass = findViewById<Spinner>(R.id.spinnerClassGrades)
        val arrayAdapterClass = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classes)
        spinnerClass.adapter = arrayAdapterClass
        spinnerClass.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                schoolClass = classes[position].toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                MainActivity.role = classes[0]
            }
        }

        // Tworzymy nasz ZadaniaAdapter i wiążemy go z listView
        adapter = TeacherTestsAdapter(this, tests, this)
        var listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

        // Wczytujemy zadania z bazy danych
        odswiezListeZadan()
    }

    var selectedDate = ""

    fun getDate(view: View) {
        val context = view.context
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                (view as Button).text = selectedDate
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }


    fun addTest(view: View) {
        Log.d("dodajZadanie","ENTER")
        // Pobieramy opis zadania
        val date = selectedDate

        // var textEdit = findViewById<EditText>(R.id.TxtClass)
        // val schoolClass = textEdit.text.toString()

        var textEdit = findViewById<EditText>(R.id.TxtSubject)
        val subject = textEdit.text.toString()

        // Konstruujemy zapytanie do bazy danych
        val jsonObject = JSONObject()
        jsonObject.put("username", MainActivity.username)
        jsonObject.put("password", MainActivity.password)
        jsonObject.put("email","")
        jsonObject.put("query",
            "INSERT INTO `tests` (date, class, subject) VALUES ('$date', '$schoolClass', '$subject')")

        val requestPOST =
            JsonObjectRequest(Request.Method.POST, url, jsonObject,
                Response.Listener { response ->
                    try {
                        // Obsługa odpowiedzi z bazy danych
                        Log.d("dodajZadanie","Response: $response")
                        textEdit.setText("")
                        // Odświeżamy całą listę zadań
                        odswiezListeZadan()
                    } catch (e:Exception){
                        Log.d("dodajZadanie","Exception: $e")
                    }
                }, Response.ErrorListener{
                    // Error in request
                    Log.d("dodajZadanie","Volley error: $it")
                })
        VolleySingleton.getInstance(this).addToRequestQueue(requestPOST)
    }

    fun odswiezListeZadan() {
        Log.d("odswiezListeZadan","ENTER")
        // Konstruujemy zapytanie do bazy danych
        val jsonObject = JSONObject()
        jsonObject.put("username", MainActivity.username)
        jsonObject.put("password", MainActivity.password)
        jsonObject.put("email","")
        jsonObject.put("query","SELECT * from `tests`")

        val requestPOST =
            JsonObjectRequest(Request.Method.POST, url,jsonObject,
                Response.Listener { response ->
                    try {
                        // Obsługa odpowiedzi z bazy danych
                        Log.d("odswiezListeZadan","Response: $response")
                        // Usuwamy wszystkie zadania z naszej listy lokalnej
                        tests.clear()
                        // Pobieramy listę zadań zwróconą z bazy danych
                        //val jsonZadania : JSONArray = response.getJSONArray("message")
                        val jsonZadania : JSONArray = JSONArray(response["message"].toString())
                        // Przechodzimy po otrzymanej liście
                        for (i in 0 until jsonZadania.length()) {
                            // Dla każdego elementu tworzymy obiekt
                            // klasy Zadanie i wstawiamy go do listy lokalnej
                            val id = jsonZadania.getJSONObject(i).getInt("id")
                            val date = jsonZadania.getJSONObject(i).getString("date")
                            val schoolClass = jsonZadania.getJSONObject(i).getString("class")
                            val subject = jsonZadania.getJSONObject(i).getString("subject")
                            print(id)
                            print(date)
                            print(schoolClass)
                            print(subject)
                            tests.add(TeacherTest(id, date, schoolClass, subject))
                        }
                        // Informujemy adapter o konieczności
                        // odświeżenia widoku
                        adapter.notifyDataSetChanged()
                    } catch (e:Exception){
                        Log.d("odswiezListeZadan","Exception: $e")
                    }

                }, Response.ErrorListener{
                    // Error in request
                    Log.d("odswiezListeZadan","Volley error: $it")
                })
        VolleySingleton.getInstance(this).addToRequestQueue(requestPOST)
    }

    override fun usunZadanie(position: Int) {
        Log.d("usunZadanie","ENTER")
        // Pobieramy kliknięte zadanie z listy
        val zadanie = adapter.getItem(position) as TeacherTest

        // Konstruujemy zapytanie do bazy danych
        val jsonObject = JSONObject()
        jsonObject.put("username", MainActivity.username)
        jsonObject.put("password", MainActivity.password)
        jsonObject.put("email","")
        jsonObject.put("query",
            "DELETE from `tests` WHERE id=${zadanie.id}")

        val requestPOST =
            JsonObjectRequest(Request.Method.POST, url,jsonObject,
                Response.Listener { response ->
                    try {
                        // Obsługa odpowiedzi z bazy danych
                        Log.d("usunZadanie","Response: $response")
                        // Odświeżamy całą listę zadań
                        odswiezListeZadan()
                    } catch (e:Exception){
                        Log.d("usunZadanie","Exception: $e")
                    }
                }, Response.ErrorListener{
                    // Error in request
                    Log.d("usunZadanie","Volley error: $it")
                })
        VolleySingleton.getInstance(this).addToRequestQueue(requestPOST)
    }

    fun goB(v: View){
        startActivity(Intent(this, TeacherMainActivity::class.java))
    }
}