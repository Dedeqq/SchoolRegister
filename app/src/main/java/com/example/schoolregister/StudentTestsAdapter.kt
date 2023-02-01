package com.example.schoolregister

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView

class StudentTestsAdapter (context: Context, tests: List<StudentTest>) :
    ArrayAdapter<StudentTest>(context, R.layout.student_test, tests) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Pobieramy z listy obiekt Zadanie do wyświelenia
        val studentTest = getItem(position) as StudentTest

        // Tworzymy widok wiersza wg naszego layout'u
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.student_test, parent, false)

        // Pobieramy referencje do pola tekstowego i przycisku
        val StudentTestsTextSubject = rowView.findViewById<TextView>(R.id.StudentTestsTextSubject)
        val StudentTestsTextDate = rowView.findViewById<TextView>(R.id.StudentTestsTextDate)


        // Ustawiamy tekstowy opis zadania
        StudentTestsTextSubject.text = studentTest.subject
        StudentTestsTextDate.text = studentTest.date

        return rowView
    }
}