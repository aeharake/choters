package com.aeharake.choters.utils

import android.text.format.DateUtils
import java.util.*

class CalendarHelper {

    companion object {
        fun getPrettyTime(time: Long): String {


            val cal = Calendar.getInstance()
            cal.timeInMillis = time
            return if (DateUtils.isToday(time)) {
                val hour = cal[Calendar.HOUR_OF_DAY]
                val minutes = cal[Calendar.MINUTE]
                "$hour:$minutes"
            } else {
                return DateUtils.getRelativeTimeSpanString(
                time, System.currentTimeMillis(), DateUtils.FORMAT_ABBREV_ALL.toLong()
            ).toString()
//                val day = cal[Calendar.DAY_OF_MONTH]
//                val month = cal[Calendar.MONTH]
//                val year = cal[Calendar.YEAR]
//                "$day/$month/$year"
            }
        }

        fun getPrettyTime(time: String): String {
            return getPrettyTime(time.toLong())
        }
    }
}