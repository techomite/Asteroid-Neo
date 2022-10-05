package com.andromite.moviessuggestions.utils

import android.app.Activity
import android.os.Build
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import java.text.DecimalFormat
import java.text.ParseException
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class Utils {

    companion object{
        fun flog(value: Any) {
            if (Constants.ENABLE_LOGS)
            Log.i("BR", ":-: $value :-:")
        }

        fun floge(value: Any) {
            if (Constants.ENABLE_LOGS)
                Log.e("asdfasdf", ":-: $value :-:")
        }

        fun hideKeyboard(activity: Activity) {
            val imm: InputMethodManager =
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            //Find the currently focused view, so we can grab the correct window token from it.
            var view: View? = activity.currentFocus
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getNumberOfDateSelected(startDate: Date, endDate: Date): Long {

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

        private fun getDateString(date: Date): String {
            val mFormat = DecimalFormat("00")

            return "${date.year}-${mFormat.format(java.lang.Double.valueOf(date.month.toString()))}-${
                mFormat.format(java.lang.Double.valueOf(date.date.toString()))}"
        }
    }
}