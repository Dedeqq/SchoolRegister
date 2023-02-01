package com.example.schoolregister

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray
import org.json.JSONObject

class StudentTestsActivity : AppCompatActivity(){

    private val tests = mutableListOf<StudentTest>()
    private lateinit var adapter : StudentTestsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_tests_activity_main)

        // Tworzymy nasz ZadaniaAdapter i wiążemy go z listView
        adapter = StudentTestsAdapter(this, tests)
        var listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

        // Wczytujemy zadania z bazy danych
        odswiezListeZadan()
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
                            val subject = jsonZadania.getJSONObject(i).getString("subject")
                            tests.add(StudentTest(id, date, subject))
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

    fun goBck(v: View){
        startActivity(Intent(this, StudentMainActivity::class.java))
    }
}