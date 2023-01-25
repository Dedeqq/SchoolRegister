package com.example.schoolregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    companion object {
        var gradesMathematics = ArrayList<Tuple>()
        var gradesPolish = ArrayList<Tuple>()
        var gradesEnglish = ArrayList<Tuple>()
        var firstName = ""
        var lastName = ""
        var studentFirstName = ""
        var studentLastName = ""
        var username = ""
        var password = ""
        var session = ""
        var role = ""
        var loggedin = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val roles = arrayOf("Uczeń", "Nauczyciel", "Rodzic", "Inna")
        val spinner = findViewById<Spinner>(R.id.spinner)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, roles)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                MainActivity.role = roles[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                MainActivity.role = "Uczeń"
            }
        }
    }

    fun onClickLogin(v: View) {
        val  user = findViewById<EditText>(R.id.loginUserEditText).text.toString()
        val  password = findViewById<EditText>(R.id.loginPasswordEditText).text.toString()

        val url = "http://10.0.2.2/androiddb/"

        getPersonalInfo(user,password,url)

        System.out.println("PRINTING FIRST NAME")
        System.out.println(MainActivity.firstName)
        System.out.println("PRINTING LAST NAME")
        System.out.println(MainActivity.lastName)

        // Post parameters
        val jsonObject = JSONObject()
        jsonObject.put("username",user)
        jsonObject.put("password",password)
        jsonObject.put("email","")
        jsonObject.put("query","")


        // Volley post request with parameters
        val requestPOST = JsonObjectRequest(Request.Method.POST,url,jsonObject,
            Response.Listener { response ->
                // Process the json
                try {
                    processResponse(response)
                    Log.d("fun onClickLogin:","Response: $response")
                }catch (e:Exception){
                    Log.d("fun onClickLogin:","Exception: $e")
                }

            }, Response.ErrorListener{
                // Error in request
                Log.d("fun onClickLogin:","Volley error: $it")
            })
        // zapamietajmy uzytegko usera i haslo - na wypadek gdyby udalo sie zalogowac
        MainActivity.username = user
        MainActivity.password = password

        VolleySingleton.getInstance(this).addToRequestQueue(requestPOST)

    }

    fun processResponse(response: JSONObject) {
        if (response["success"]==1) {
            Toast.makeText(this, "Zalogowano pomyślnie", Toast.LENGTH_LONG).show()
            MainActivity.loggedin = true
            MainActivity.session = response["session"].toString()
            when (MainActivity.role) {
                "Uczeń" -> intent = Intent(this, StudentMainActivity::class.java)
                "Nauczyciel" -> intent = Intent(this, TeacherMainActivity::class.java)
                "Rodzic" -> intent = Intent(this, ParentMainActivity::class.java)
                "Inna" -> intent = Intent(this, OtherMainActivity::class.java)
            }
            startActivity(intent)
        }
        if (response["success"]==0) {
            MainActivity.loggedin = false
            MainActivity.session = ""
            MainActivity.username = ""
            MainActivity.password =""
            Toast.makeText(this, response["message"].toString(), Toast.LENGTH_LONG).show()
        }

    }

    fun getPersonalInfo(user: String, password: String, url: String){
        System.out.println("START SECRET FUNCTION")
        val query = "SELECT users.*, \n" +
                "\tCOALESCE(parents.firstname, students.firstname, teachers.firstname, others.firstname) AS firstname,  \n" +
                "    COALESCE(parents.lastname, students.lastname, teachers.lastname, others.lastname) AS lastname FROM users \n" +
                "LEFT JOIN parents ON parents.user_id = users.id\n" +
                "LEFT JOIN students ON students.user_id = users.id\n" +
                "LEFT JOIN teachers ON teachers.user_id = users.id\n" +
                "LEFT JOIN others ON others.user_id = users.id\n" +
                "WHERE users.username = \""+user+"\""
        val jsonObject2 = JSONObject()
        jsonObject2.put("username",user)
        jsonObject2.put("password",password)
        jsonObject2.put("email","")
        jsonObject2.put("query",query)

        val requestPOST = JsonObjectRequest(Request.Method.POST,url,jsonObject2,
            Response.Listener { response ->
                // Process the json
                try {
                    val jsonArray = JSONArray(response["message"].toString())
                    val jsonObject = jsonArray.getJSONObject(0)
                    MainActivity.firstName = jsonObject["firstname"].toString()
                    MainActivity.lastName = jsonObject["lastname"].toString()
                    System.out.println(MainActivity.firstName)
                    System.out.println(MainActivity.lastName)
                    Log.d("fun secretFunction:","Response: $response")
                }catch (e:Exception){
                    Log.d("fun secretFunction:","Exception: $e")
                }

            }, Response.ErrorListener{
                // Error in request
                Log.d("fun secretFunction:","Volley error: $it")
            })
        VolleySingleton.getInstance(this).addToRequestQueue(requestPOST)
        System.out.println("END SECRET FUNCTION")
    }

}