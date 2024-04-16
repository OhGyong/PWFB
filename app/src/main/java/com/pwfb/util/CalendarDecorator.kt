package com.pwfb.util

import android.graphics.drawable.Drawable
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade


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
class TodayDecorator(
    private var color: Int,
    private var drawable: Drawable
) :DayViewDecorator {
    private var date = CalendarDay.today()
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.equals(date)!!
    }
    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(object: ForegroundColorSpan(color){})
        view?.setBackgroundDrawable(drawable)
    }
}

/**
 * 날짜 선택 시 캘린더 데코
 */
class SelectDecorator(
    private var color: Int,
    private var drawable: Drawable,
    private var date: CalendarDay
) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day == date
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(object: ForegroundColorSpan(color){})
        view.setSelectionDrawable(drawable)
    }
}


/**
 * 날짜 선택 전에 캘린더 데코 초기화
 */
class ClearDecorator(private var color: Int, private var date: CalendarDay) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day!=date
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(object: ForegroundColorSpan(color){})
    }
}