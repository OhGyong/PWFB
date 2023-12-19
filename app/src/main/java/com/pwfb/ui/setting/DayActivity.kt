package com.pwfb.ui.setting

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
import com.pwfb.common.DataStoreResult
import com.pwfb.databinding.ActivityDayBinding
import com.pwfb.ui.MainActivity
import com.pwfb.util.ClearDecorator
import com.pwfb.util.DayDisableDecorator
import com.pwfb.util.SelectDecorator
import com.pwfb.util.TodayDecorator
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.util.Calendar


@AndroidEntryPoint
class DayActivity : BaseActivity() {

    private lateinit var binding: ActivityDayBinding
    private val viewModel: DayViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(PWFB, "WeightActivity onCreate")

        super.onCreate(savedInstanceState)
        binding = ActivityDayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCalendarView()
        getLocalTime()

        binding.btGo.setOnClickListener {
            viewModel.setDDay(binding.btTime.text.toString())
        }

        viewModel.dDayObserve.observe(this) {
            if(it == DataStoreResult.SET_D_DAY) {
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
        }
    }

    private fun getLocalTime() {
        var timeText = ""
        val localTime = LocalDateTime.now().toLocalTime()

        timeText += if(localTime.hour < 12) {
            "오전" + " " + localTime.hour + ":" + localTime.minute
        }else {
            "오후" + " " + (localTime.hour-12) + ":" + localTime.minute
        }

        binding.btTime.text = timeText

        binding.btTime.setOnClickListener {
            setTimeSpinner()
        }
    }

    private fun setTimeSpinner() {
        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            binding.btTime.text = if(hour < 12) {
                "오전 $hour:$minute"
            }else {
                "오후" + " " + (hour-12) + ":" + minute
            }
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
}