package com.andromite.asteroidneo.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andromite.asteroidneo.network.ApiClient
import com.andromite.asteroidneo.network.models.AsteroidDetails
import com.andromite.asteroidneo.network.models.DateNeoDetails
import com.andromite.moviessuggestions.utils.Utils
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.ParseException
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MainViewModel : ViewModel() {

    val dateNeoDetailsLiveData = MutableLiveData<MutableList<DateNeoDetails>>()


    @RequiresApi(Build.VERSION_CODES.O)
    fun getNeoResponse(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            Utils.floge("start date: ${getDateString(startDate)} end date: ${getDateString(endDate)}")
            val response = ApiClient().apiClient.getNeoResponse(
                getDateString(startDate),
                getDateString(endDate), "yJ6Zykvhu3GEg5MZx0INfISBdohufoUggVnM2Gd7"
            )

            val json = response.body()?.nearEarthObjects

            val noOfDays = getNumberOfDateSelected(startDate, endDate)
            Utils.floge("number of days selected: $noOfDays")
            var currentDate = startDate
            val dateNeoDetails =  mutableListOf<DateNeoDetails>()

            for (i in 0..noOfDays){
                val arrList = json?.getAsJsonArray(getDateString(currentDate))
                val tempList = mutableListOf<AsteroidDetails>()
                if (arrList != null) {
                    for (asteroidDetails in arrList){
                        val astro = Gson().fromJson(asteroidDetails, AsteroidDetails::class.java)
                        tempList.add(astro)

                    }
                }
                val dateNeo = DateNeoDetails(getDateString(currentDate), tempList)
                dateNeoDetails.add(dateNeo)
                currentDate = increaseDate(currentDate)
            }

            dateNeoDetailsLiveData.postValue(dateNeoDetails)
        }
    }

    private fun increaseDate(date: Date): Date {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.DATE, 1) //minus number would decrement the days

        return cal.time
    }

    private fun getDateString(date: Date): String {
        val mFormat = DecimalFormat("00")

        return "${date.year}-${mFormat.format(java.lang.Double.valueOf(date.month.toString()))}-${
            mFormat.format(java.lang.Double.valueOf(date.date.toString()))}"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getNumberOfDateSelected(startDate: Date, endDate: Date): Long {

        val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val inputString1 = getDateString(startDate)
        val inputString2 = getDateString(endDate)

        try {
            val date1: LocalDate? = LocalDate.parse(inputString1, dtf)
            val date2: LocalDate? = LocalDate.parse(inputString2, dtf)
            return Duration.between(date1?.atStartOfDay(), date2?.atStartOfDay()).toDays()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }

}