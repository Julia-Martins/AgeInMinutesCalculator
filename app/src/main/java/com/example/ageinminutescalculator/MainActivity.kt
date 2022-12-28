package com.example.ageinminutescalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var txtDateOnScreen: TextView? = null
    private var txtDateMinute: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSelectDate: Button = findViewById(R.id.btnSelectDate)
        txtDateOnScreen = findViewById(R.id.txtDataOnScreen)
        txtDateMinute = findViewById(R.id.txtDateMinute)

        btnSelectDate.setOnClickListener {
            clickDatePicker()
        }
    }

// region Function Calendar
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val finalResult = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener
            { _, selectYear, selectMonth, selectDay ->
                Toast.makeText(this, "Year was $selectYear, month was ${selectMonth+1}," +
                        " and day is $selectDay", Toast.LENGTH_SHORT).show()

                val selectedDate = "$selectDay/${selectMonth+1}/$selectYear"

                txtDateOnScreen?.text = selectedDate

                val sdf = SimpleDateFormat("dd/mm/yyyy")

                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentDateInMin = currentDate.time / 60000
                        val differenceInMinutes = currentDateInMin - selectedDateInMinutes

                        txtDateMinute?.text = differenceInMinutes.toString()
                    }
                }

            },
            year, month, day
        )
        finalResult.datePicker.maxDate = System.currentTimeMillis() - 86400000
        finalResult.show()
    }
// endregion

// region Comment Apart the Video
//            This is a form shorter to do the same code, it's better if I know exactly what type I use in the code
//            {view, year, month, day ->
//                Toast.makeText(this, "The Method Works", Toast.LENGTH_SHORT).show()
//            }
// endregion
}