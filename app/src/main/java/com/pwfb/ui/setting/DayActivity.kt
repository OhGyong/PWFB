package com.pwfb.ui.setting

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
import com.pwfb.R
import com.pwfb.base.BaseActivity
import com.pwfb.databinding.ActivityDayBinding
import com.pwfb.domain.entity.PwfbResultEntity
import com.pwfb.ui.MainActivity
import com.pwfb.util.ClearDecorator
import com.pwfb.util.DayDisableDecorator
import com.pwfb.util.SelectDecorator
import com.pwfb.util.TodayDecorator
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar


@AndroidEntryPoint
class DayActivity : BaseActivity() {

    private lateinit var binding: ActivityDayBinding
    private val viewModel: DayViewModel by viewModels()

    private var datePref = ""
    private var timePref = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(PWFB, "WeightActivity onCreate")

        super.onCreate(savedInstanceState)
        binding = ActivityDayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCalendarView()
        getLocalTime()

        binding.btTime.setOnClickListener {
            setTimeSpinner()
        }

        binding.btGo.setOnClickListener {
            viewModel.setDDay("$datePref $timePref")
        }

        viewModel.dDayObserve.observe(this) {
            if(it == PwfbResultEntity.Success(RESULT_OK)) {
                viewModel.setFirstInit()
            }
        }

        viewModel.firstInitObserve.observe(this) {
            if(it == PwfbResultEntity.Success(RESULT_OK)) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setCalendarView() {
        // 연, 월 헤더 스타일
        binding.cvCalendar.setHeaderTextAppearance(R.style.CalendarWidgetHeader)

        // 1~12월, 월간 표시
        binding.cvCalendar.setTitleFormatter(MonthArrayTitleFormatter(resources.getTextArray(R.array.calendar_month)))

        // 일~토, 주간 표시
        binding.cvCalendar.setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.calendar_week)))

        // 헤더 년 월로 표시
        binding.cvCalendar.setTitleFormatter { day ->
            val inputText = day.date
            val calendarHeaderElements = inputText.toString().split("-")
            val calendarHeaderBuilder = StringBuilder()

            calendarHeaderBuilder.append(calendarHeaderElements[0]).append("년 ")
                .append(calendarHeaderElements[1]).append("월")

            calendarHeaderBuilder.toString()
        }

        val today = CalendarDay.today()
        val disabledDates = hashSetOf<CalendarDay>()

        val dayDisableDecorator = DayDisableDecorator(disabledDates, today, getColor(R.color.c_949292))
        val todayDecorator = TodayDecorator(getColor(R.color.c_caab3f))

        binding.cvCalendar.addDecorators(dayDisableDecorator, todayDecorator)

        // 날짜 선택 시 처리
        binding.cvCalendar.setOnDateChangedListener { _, date, _ ->
            binding.cvCalendar.addDecorators(
                ClearDecorator(getColor(R.color.white), date),
                dayDisableDecorator, todayDecorator,
                SelectDecorator(getColor(R.color.c_caab3f), date)
            )

            binding.btGo.isEnabled = true
            binding.btGo.setTextColor(getColor(R.color.white))

            val month = if(date.month<10) "0${date.month}" else "${date.month}"
            val day = if(date.day<10) "0${date.day}" else "${date.day}"

            datePref = "${date.year}.$month.$day${getWeek(date)}"
        }
    }

    private fun getLocalTime() {
        val localTime = LocalDateTime.now().toLocalTime()

        val amPm = if(localTime.hour < 12) "오전" else "오후"

        val hour = if(amPm == "오후") {
            if((localTime.hour-12)<10) "0${(localTime.hour-12)}" else "${(localTime.hour-12)}"
        } else {
            if(localTime.hour<10) "0${localTime.hour}" else "${localTime.hour}"
        }

        val minute = if(localTime.minute<10) "0${localTime.minute}" else "${localTime.minute}"

        @SuppressLint("SetTextI18n")
        binding.btTime.text = "$amPm $hour:$minute"
        timePref = if(amPm == "오후") "${localTime.hour}:$minute⏳" else "$hour:$minute⏳"
    }


    private fun setTimeSpinner() {
        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, pHour, pMinute ->
            cal.set(Calendar.HOUR_OF_DAY, pHour)
            cal.set(Calendar.MINUTE, pMinute)

            val amPm = if(pHour < 12) "오전" else "오후"

            val hour = if(amPm == "오후") {
                if((pHour-12)<10) "0${(pHour-12)}" else "${(pHour-12)}"
            } else {
                if(pHour<10) "0$pHour" else "$pHour"
            }

            val minute = if(pMinute<10) "0$pMinute" else "$pMinute"

            @SuppressLint("SetTextI18n")
            binding.btTime.text = "$amPm $hour:$minute"
            timePref = "$hour:$minute⏳"
        }

        val timePickerDialog = TimePickerDialog(
            this,
            R.style.TimePickerTheme,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            false
        )
        timePickerDialog.setButton(TimePickerDialog.BUTTON_POSITIVE, getString(R.string.confirm), timePickerDialog)
        timePickerDialog.setButton(TimePickerDialog.BUTTON_NEGATIVE, getString(R.string.cancel), timePickerDialog)
        timePickerDialog.show()
    }

    private fun getWeek(date: CalendarDay):String {
        return when(LocalDate.of(date.year, date.month, date.day).dayOfWeek.value) {
            1 -> "(월)"
            2 -> "(화)"
            3 -> "(수)"
            4 -> "(목)"
            5 -> "(금)"
            6 -> "(토)"
            7 -> "(일)"
            else -> ""
        }
    }
}