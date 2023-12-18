package com.pwfb.util

import android.content.Context
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.pwfb.R


/**
 * 오늘 이전 날짜 처리
 */
class DayDisableDecorator(
    private var dates: HashSet<CalendarDay>,
    private var today: CalendarDay,
    private var color: Int
) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.contains(day) || day.isBefore(today)
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(object: ForegroundColorSpan(color){})
        view?.setDaysDisabled(true)
    }
}

/**
 * 오늘 날짜 표시
 */
class TodayDecorator(private var color: Int) :DayViewDecorator {
    private var date = CalendarDay.today()
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.equals(date)!!
    }
    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(object: ForegroundColorSpan(color){})
    }
}

class SelectDecorator(private var color: Int, private var date: CalendarDay) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day == date
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(object: ForegroundColorSpan(color){})
    }
}

class ClearDecorator(private var color: Int, private var date: CalendarDay) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day!=date
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(object: ForegroundColorSpan(color){})
    }
}