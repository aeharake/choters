package github.aeharake.choters.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

class CalendarHelper {

    companion object {
        private fun getPrettyTime(time: Long): String {


            val cal = Calendar.getInstance()
            cal.timeInMillis = time
            return if (DateUtils.isToday(time)) {
                 SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(time))
            } else {
                return DateUtils.getRelativeTimeSpanString(
                time, System.currentTimeMillis(), DateUtils.FORMAT_ABBREV_ALL.toLong()
            ).toString()
            }
        }

        fun getPrettyTime(time: String): String {
            return getPrettyTime(time.toLong())
        }
    }
}