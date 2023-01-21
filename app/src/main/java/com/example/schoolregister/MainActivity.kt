package com.example.schoolregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
class MainActivity : AppCompatActivity() {

    companion object {
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
}