package com.pwfb.ui.screen

import android.graphics.drawable.Drawable
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.pwfb.R
import com.pwfb.common.ScreenName.SCREEN_DAY
import com.pwfb.common.ScreenName.SCREEN_WEIGHT
import com.pwfb.theme.DarkGray
import com.pwfb.theme.FiledTypography
import com.pwfb.theme.Gray
import com.pwfb.theme.SettingTitleTypography
import com.pwfb.theme.White
import com.pwfb.theme.Yellow40
import com.pwfb.ui.viewmodel.DayViewModel
import com.pwfb.util.ClearDecorator
import com.pwfb.util.DayDisableDecorator
import com.pwfb.util.SelectDecorator
import com.pwfb.util.TodayDecorator

@Composable
fun DdayScreen(
    navController: NavHostController,
    calendarDrawable: List<Drawable?>,
    dayViewModel: DayViewModel = hiltViewModel()
) {
    BackHandler(enabled = true) {
        navController.navigate(SCREEN_WEIGHT) {
            popUpTo(SCREEN_DAY) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.d_day),
            style = SettingTitleTypography.bodyLarge
        )

        CalendarView(calendarDrawable)

        TimeView()
    }
}

@Composable
fun CalendarView(calendarDrawable: List<Drawable?>) {
    val today = CalendarDay.today()
    val disabledDates = hashSetOf<CalendarDay>()

    AndroidView(
        factory = { MaterialCalendarView(it) },
        update = { calendarView ->
            calendarView.setHeaderTextAppearance(R.style.CalendarWidgetHeader)
            calendarView.setWeekDayTextAppearance(R.style.CalenderViewWeekCustomText)
            calendarView.setDateTextAppearance(R.style.CalenderViewDateCustomText)

            calendarView.setTitleFormatter { day ->
                val inputText = day.date
                val calendarHeaderElements = inputText.toString().split("-")
                val calendarHeaderBuilder = StringBuilder()

                calendarHeaderBuilder.append(calendarHeaderElements[0]).append("년 ")
                    .append(calendarHeaderElements[1]).append("월")

                calendarHeaderBuilder.toString()
            }

            val dayDisableDecorator = DayDisableDecorator(disabledDates, today, Gray.toArgb())
            val todayDecorator = calendarDrawable[0]?.let { TodayDecorator(Yellow40.toArgb(), it) }

            calendarView.addDecorators(dayDisableDecorator, todayDecorator)

            calendarView.setOnDateChangedListener { _, date, _ ->
                calendarView.addDecorators(
                    ClearDecorator(White.toArgb(), date),
                    dayDisableDecorator,
                    todayDecorator,
                    calendarDrawable[1]?.let { SelectDecorator(Yellow40.toArgb(), it, date) }
                )
            }
        })
}

@Composable
fun TimeView() {
    Row(
        modifier = Modifier,
    ) {
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = stringResource(id = R.string.time),
            style = FiledTypography.bodyMedium
       )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(DarkGray),
                shape = RoundedCornerShape(10.dp),
                onClick = {}
            ) {
            }
        }
    }
}
