package com.example.pppb_t6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.pppb_t6.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var selectedDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date())
    var selectedTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            datePicker.setOnDateChangeListener { _, year, month, dayOfMonth ->
                val months = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
                selectedDate = "$dayOfMonth ${months[month]} $year"
            }

            timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
                val formattedHour = if (hourOfDay % 12 == 0) 12 else hourOfDay % 12
                val formattedMinute = DecimalFormat("00").format(minute)
                val amPm = if (hourOfDay >= 12) "PM" else "AM"
                selectedTime = "$formattedHour:$formattedMinute $amPm"
            }

            spinnerAttendance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (position > 0) {
                        absenceInfo.visibility = View.VISIBLE
                    } else {
                        absenceInfo.visibility = View.GONE
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }

            submitBtn.setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "Presensi berhasil $selectedDate jam $selectedTime",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}