package com.example.schoolregister

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray
import org.json.JSONObject

class ParentMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parent_activity_main)
        val greetings: TextView = findViewById(R.id.greetings)
        greetings.setText("Witaj "+MainActivity.firstName+" "+MainActivity.lastName+"!")
    }

    fun onClickScheduleParent(v: View){
        startActivity(Intent(this, ScheduleActivity::class.java))
    }

    fun onClickTestsParent(v: View){
        startActivity(Intent(this, StudentTestsActivity::class.java))
    }

    fun onClickLogoutParent(v: View) {
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

    fun onClickGradesParent(v: View) {
        println("ok")
        if (MainActivity.lastName == "Kapusta") MainActivity.studentFirstName = "Mateusz"
        if (MainActivity.lastName == "Kogut") MainActivity.studentFirstName = "Jan"
        MainActivity.studentLastName = MainActivity.lastName
        getGradesInfo()

    }

    fun getGradesInfo(){
        val url = "http://10.0.2.2/androiddb/"
        val query = "SELECT students.*, grades.*, subjects.* FROM grades RIGHT JOIN students on students.id = grades.student_id LEFT JOIN subjects on grades.subject_id = subjects.id WHERE students.firstname = \""+MainActivity.studentFirstName +"\" AND students.lastname = \""+MainActivity.studentLastName+"\""

        // Post parameters
        var jsonObject = JSONObject()
        jsonObject.put("username",MainActivity.username)
        jsonObject.put("password",MainActivity.password)
        jsonObject.put("email","")
        jsonObject.put("query",query)
        println(query)

        val requestPOST = JsonObjectRequest(Request.Method.POST,url,jsonObject,
            Response.Listener { response ->
                // Process the json
                try {
                    val jsonArray = JSONArray(response["message"].toString())
                    MainActivity.gradesPolish.clear()
                    MainActivity.gradesEnglish.clear()
                    MainActivity.gradesMathematics.clear()
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        if (jsonObject["subject"].toString()=="MATEMATYKA"){
                            MainActivity.gradesMathematics.add(Tuple(jsonObject["grade"].toString(),jsonObject["weight"].toString(),jsonObject["description"].toString()))
                        }
                        if (jsonObject["subject"].toString()=="J??ZYK POLSKI"){
                            MainActivity.gradesPolish.add(Tuple(jsonObject["grade"].toString(),jsonObject["weight"].toString(),jsonObject["description"].toString()))
                        }
                        if (jsonObject["subject"].toString()=="J??ZYK ANGIELSKI"){
                            MainActivity.gradesEnglish.add(Tuple(jsonObject["grade"].toString(),jsonObject["weight"].toString(),jsonObject["description"].toString()))
                        }
                        startActivity(Intent(this, GradesActivity::class.java))
                    }

                    Log.d("fun getGradesInfo:","Response: $response")

                }catch (e:Exception){
                    Log.d("fun getGradesInfo:","Exception: $e")
                }

            }, Response.ErrorListener{
                // Error in request
                Log.d("fun getGradesInfo:","Volley error: $it")
            })
        VolleySingleton.getInstance(this).addToRequestQueue(requestPOST)
    }
}