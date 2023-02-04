package com.example.schoolregister

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PasswordActivity : AppCompatActivity() {

    val mails = arrayOf("uczen1@uczen1.pl", "uczen2@uczen2.pl", "rodzic1@rodzic1.pl", "inna1@inna1.pl",
                        "rodzic2@rodzic2.pl", "nauczyciel1@nauczyciel1.pl", "nauczyciel21@nauczyciel2.pl")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_activity_main)
    }

    fun onClickf1(v: View){
        val  mail = findViewById<EditText>(R.id.editTextPassword).text.toString()

        if (mails.contains(mail)) {
            Toast.makeText(this, "Hasło zostało zresetowane", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Niepoprawne dane", Toast.LENGTH_LONG).show()
        }
    }

    fun onClickf2(v: View){
        startActivity(Intent(this, MainActivity::class.java))
    }
}