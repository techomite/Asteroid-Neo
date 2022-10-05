package com.andromite.asteroidneo.ui

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.andromite.asteroidneo.databinding.ActivityMainBinding
import com.andromite.moviessuggestions.utils.Utils
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
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
//        setData(45, 180f)

    }

    private fun registerDataRequest() {
        viewModel.dateNeoDetailsLiveData.observe(this){
            Utils.floge("size of details list : ${it.size}")

            val entryList = mutableListOf<Entry>()

            for (dateDetails in it){
                Utils.floge("x: ${dateDetails.date.toFloat()} y: ${dateDetails.asteroidDetailList!!.size.toFloat()}")
                val entry = Entry(dateDetails.date.toFloat(), dateDetails.asteroidDetailList.size.toFloat())
                entryList.add(entry)
            }

            Utils.floge("entry list: $entryList")
            runOnUiThread {
                binding.progressbar.visibility = View.GONE
                binding.detailsLayout.visibility = View.VISIBLE

                binding.fastTextView.text = viewModel.getFastestAsteroid()
                binding.closeTextView.text = viewModel.getClosestAsteroid()
                binding.averageTextView.text = viewModel.getAverageSizes()
                setData(entryList)
//                setData(45, 180f)
            }
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
                { _, year, month, mdayOfMonth ->
                    startDate.year = year
                    startDate.month = month
                    startDate.date = mdayOfMonth

                    binding.startDateET.text = "$year-$month-$mdayOfMonth"
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
                { _, year, month, mdayOfMonth ->
                    endDate.year = year
                    endDate.month = month
                    endDate.date = mdayOfMonth
                    binding.endDateET.text = "$year-$month-$mdayOfMonth"
                },
                year,
                month,
                day
            )
            datePickerDialog.show()

        }

        binding.submitButton.setOnClickListener {

            val days = Utils.getNumberOfDateSelected(startDate, endDate)
            if(days > 7)
                Toast.makeText(this, "Select dates between 7 days span", Toast.LENGTH_SHORT).show()
            else if (binding.startDateET.text == "" && binding.endDateET.text == ""){
                Toast.makeText(this, "Enter Start & End Date", Toast.LENGTH_SHORT).show()
            } else {
                binding.progressbar.visibility = View.VISIBLE
                viewModel.getNeoResponse(startDate, endDate)
            }
        }

        binding.lineChart.setTouchEnabled(false)
    }

    private fun setData(values: MutableList<Entry>) {

        val set1: LineDataSet
        if (binding.lineChart.data != null &&
            binding.lineChart.data.dataSetCount > 0
        ) {
            set1 = binding.lineChart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            set1.notifyDataSetChanged()
            binding.lineChart.data.notifyDataChanged()
            binding.lineChart.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "Asteroid Neo")
            set1.setDrawIcons(false)



            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f)

            // black lines and points
            set1.color = Color.BLACK
            set1.setCircleColor(Color.BLACK)

            // line thickness and point size
            set1.lineWidth = 1f
            set1.circleRadius = 3f

            // draw points as solid circles
            set1.setDrawCircleHole(false)

            // customize legend entry
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f

            // text size of values
            set1.valueTextSize = 9f

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f)

            // set the filled area
            set1.setDrawFilled(true)
            set1.fillFormatter =
                IFillFormatter { _, _ -> binding.lineChart.axisLeft.axisMinimum }

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1) // add the data sets

            // create a data object with the data sets
            val data = LineData(dataSets)

            // set data
            binding.lineChart.data = data
            binding.lineChart.visibility = View.VISIBLE
        }
    }

}