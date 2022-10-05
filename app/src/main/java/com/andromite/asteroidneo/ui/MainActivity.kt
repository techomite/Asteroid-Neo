package com.andromite.asteroidneo.ui

import android.R
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.andromite.asteroidneo.databinding.ActivityMainBinding
import com.andromite.moviessuggestions.utils.Utils
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    private lateinit var startDate : Date
    private lateinit var endDate : Date


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        startDate = Date()
        endDate = Date()

        initViews()
        registerDataRequest()

    }

    private fun registerDataRequest() {
        viewModel.dateNeoDetailsLiveData.observe(this){
            Utils.floge("size of details list : ${it.size}")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViews() {


        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        binding.startDateLayout.setOnClickListener {

            val datePickerDialog = DatePickerDialog(
                this,
                { view, myear, mmonth, mdayOfMonth ->
                    startDate.year = myear
                    startDate.month = mmonth
                    startDate.date = mdayOfMonth

                    binding.startDateET.text = "$myear-$mmonth-$mdayOfMonth"
                },
                year,
                month,
                day
            )
            datePickerDialog.show()

        }

        binding.endDateLayout.setOnClickListener {

            val datePickerDialog = DatePickerDialog(
                this,
                { view, myear, mmonth, mdayOfMonth ->
                    endDate.year = myear
                    endDate.month = mmonth
                    endDate.date = mdayOfMonth
                    binding.endDateET.text = "$myear-$mmonth-$mdayOfMonth"
                },
                year,
                month,
                day
            )
            datePickerDialog.show()

        }

        binding.submitButton.setOnClickListener {
            if (binding.startDateET.text == "" && binding.endDateET.text == ""){
                Toast.makeText(this, "Enter Start & End Date", Toast.LENGTH_SHORT).show()
            } else {
                binding.progressbar.visibility = View.VISIBLE
                viewModel.getNeoResponse(startDate, endDate)
            }
        }

        binding.lineChart.setTouchEnabled(false)
    }


}