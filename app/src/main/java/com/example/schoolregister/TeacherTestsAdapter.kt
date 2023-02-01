package com.example.schoolregister

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.schoolregister.TeacherTest

class TeacherTestsAdapter(context: Context, tests: List<TeacherTest>,
                          val usunZadanieListener : UsunZadanieListener) :
    ArrayAdapter<TeacherTest>(context, R.layout.teacher_test, tests) {

    // Interfejs, który musi implementować klasa, która będzie
    // usuwać dla nas zadania (w naszym przypadku ZadaniaActivity)
    interface UsunZadanieListener {
        fun usunZadanie(position: Int)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Pobieramy z listy obiekt Zadanie do wyświelenia
        val teacherTest = getItem(position) as TeacherTest

        // Tworzymy widok wiersza wg naszego layout'u
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.teacher_test, parent, false)

        // Pobieramy referencje do pola tekstowego i przycisku
        val TeacherTestsTextSubject = rowView.findViewById<TextView>(R.id.TeacherTestsTextSubject)
        val TeacherTestsTextClass = rowView.findViewById<TextView>(R.id.TeacherTestsTextClass)
        val TeacherTestsTextDate = rowView.findViewById<TextView>(R.id.TeacherTestsTextDate)

        val buttonUsunZadanie = rowView.findViewById<Button>(R.id.buttonUsunZadanie)

        // Ustawiamy tekstowy opis zadania
        TeacherTestsTextSubject.text = teacherTest.subject
        TeacherTestsTextClass.text = teacherTest.schoolClass
        TeacherTestsTextDate.text = teacherTest.date


        // Ustawiamy akcję obsługi kliknięcia przycisku
        buttonUsunZadanie.setOnClickListener { v : View ->
            if (usunZadanieListener != null) {
                // Metoda usunZadanie z klasy ZadaniaActivity
                // zostanie wywołana z odpowiednią wartością
                // parametru position, co pozwoli nam zidentyfikować
                // zadanie do usunięcia
                usunZadanieListener.usunZadanie(position)
            }
        }

        return rowView
    }
}