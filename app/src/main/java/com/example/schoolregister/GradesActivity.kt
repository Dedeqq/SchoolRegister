package com.example.schoolregister

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray
import org.json.JSONObject
import kotlinx.coroutines.*

class GradesActivity : AppCompatActivity() {

    private lateinit var customAdapter1: CustomAdapter
    private lateinit var customAdapter2: CustomAdapter
    private lateinit var customAdapter3: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grades_activity_main)

        System.out.println(MainActivity.gradesEnglish.size)
        System.out.println(MainActivity.gradesMathematics.size)
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

    fun getGradesInfo(){
        val url = "http://10.0.2.2/androiddb/"

        val query = "SELECT students.*, grades.*, subjects.* FROM grades\n" +
                "RIGHT JOIN students on students.id = grades.student_id\n" +
                "LEFT JOIN subjects on grades.subject_id = subjects.id\n" +
                "WHERE students.firstname = \""+MainActivity.studentFirstName+"\" AND students.lastname = \""+MainActivity.studentLastName+"\""

        val query_ = "SELECT students.*, grades.*, subjects.* FROM grades RIGHT JOIN students on students.id = grades.student_id LEFT JOIN subjects on grades.subject_id = subjects.id WHERE students.firstname = \"UCZEN1_IMIE\" AND students.lastname = \"UCZEN1_NAZWISKO\";"

        // Post parameters
        val jsonObject = JSONObject()
        jsonObject.put("username",MainActivity.username)
        jsonObject.put("password",MainActivity.password)
        jsonObject.put("email","")
        jsonObject.put("query",query_)


        val requestPOST = JsonObjectRequest(Request.Method.POST,url,jsonObject,
            Response.Listener { response ->
                // Process the json
                try {
                    val jsonArray = JSONArray(response["message"].toString())
                    MainActivity.gradesPolish.clear()
                    MainActivity.gradesEnglish.clear()
                    MainActivity.gradesMathematics.clear()
                    System.out.println(response["message"])
                    // grade, grade_value, weight, subject, description
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        //System.out.println(jsonObject)
                        //System.out.println(jsonObject["subject"])
                        //System.out.println(jsonObject["grade"])
                        //System.out.println(jsonObject["weight"])
                        //System.out.println(jsonObject["description"])
                        System.out.println(jsonObject["subject"])
                        System.out.println(jsonObject["subject"]=="MATEMATYKA")
                        if (jsonObject["subject"].toString()=="MATEMATYKA"){
                            MainActivity.gradesMathematics.add(Tuple(jsonObject["grade"].toString(),jsonObject["weight"].toString(),jsonObject["description"].toString()))
                        }
                        if (jsonObject["subject"].toString()=="JĘZYK POLSKI"){
                            MainActivity.gradesPolish.add(Tuple(jsonObject["grade"].toString(),jsonObject["weight"].toString(),jsonObject["description"].toString()))
                        }
                        if (jsonObject["subject"].toString()=="JĘZYK ANGIELSKI"){
                            MainActivity.gradesEnglish.add(Tuple(jsonObject["grade"].toString(),jsonObject["weight"].toString(),jsonObject["description"].toString()))
                        }

                    }

                    Log.d("fun secretFunction:","Response: $response")

                }catch (e:Exception){
                    Log.d("fun secretFunction:","Exception: $e")
                }

            }, Response.ErrorListener{
                // Error in request
                Log.d("fun secretFunction:","Volley error: $it")
            })
        VolleySingleton.getInstance(this).addToRequestQueue(requestPOST)


    }
}