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
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
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
//        setData(45, 180f)

    }

    private fun registerDataRequest() {
        viewModel.dateNeoDetailsLiveData.observe(this){
            Utils.floge("size of details list : ${it.size}")

            val entryList = mutableListOf<Entry>()

            for (dateDetails in it){
                Utils.floge("x: ${dateDetails.date.toFloat()} y: ${dateDetails.asteroidDetailList!!.size.toFloat()}")
                val entry = Entry(dateDetails.date.toFloat(), dateDetails.asteroidDetailList!!.size.toFloat())
                entryList.add(entry)
            }

            Utils.floge("entry list: $entryList")
            runOnUiThread {
                binding.progressbar.visibility = View.GONE
                binding.detailsLayout.visibility = View.VISIBLE

                binding.fastTextView.text = viewModel.getFastestAsteroid()
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

    private fun setData(values: MutableList<Entry>) {

        val set1: LineDataSet
        if (binding.lineChart.getData() != null &&
            binding.lineChart.getData().getDataSetCount() > 0
        ) {
            set1 = binding.lineChart.getData().getDataSetByIndex(0) as LineDataSet
            set1.values = values
            set1.notifyDataSetChanged()
            binding.lineChart.getData().notifyDataChanged()
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
                IFillFormatter { dataSet, dataProvider -> binding.lineChart.getAxisLeft().getAxisMinimum() }

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1) // add the data sets

            // create a data object with the data sets
            val data = LineData(dataSets)

            // set data
            binding.lineChart.setData(data)
            binding.lineChart.visibility = View.VISIBLE
        }
    }

}