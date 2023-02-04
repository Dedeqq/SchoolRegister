package com.example.schoolregister

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class OtherMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.other_activity_main)
        val greetings: TextView = findViewById(R.id.greetings)
        greetings.setText("Witaj "+MainActivity.firstName+" "+MainActivity.lastName+"!")
    }

    fun onClickA(v: View) {
        var query = "INSERT INTO author (firstname,lastname) VALUES (\"Biedroń\",\"Martin\");"
        val jsonObject = JSONObject()
        jsonObject.put("username",MainActivity.username)
        jsonObject.put("password",MainActivity.password)
        jsonObject.put("email","")
        jsonObject.put("query",query)
        val url = "http://10.0.2.2/androiddb/"
        val requestPOST = JsonObjectRequest(Request.Method.POST,url,jsonObject,
            Response.Listener { response ->
                // Process the json
                try {

                    Log.d("fun onClickQuery:","Response: $response")
                }catch (e:Exception){
                    Log.d("fun onClickQuery:","Exception: $e")
                }

            }, Response.ErrorListener{
                // Error in request
                Log.d("fun onClickQuery:","Volley error: $it")
            })
        VolleySingleton.getInstance(this).addToRequestQueue(requestPOST)
    }

}