package com.pwfb.ui.screen

import android.app.TimePickerDialog
import android.graphics.drawable.Drawable
import android.widget.TimePicker
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.pwfb.R
import com.pwfb.common.ScreenName.SCREEN_DAY
import com.pwfb.common.ScreenName.SCREEN_WEIGHT
import com.pwfb.theme.DarkGray
import com.pwfb.theme.FiledTypography
import com.pwfb.theme.Gray
import com.pwfb.theme.Pink80
import com.pwfb.theme.Purple40
import com.pwfb.theme.Purple80
import com.pwfb.theme.PurpleGrey80
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeView() {
    Row(
        modifier = Modifier,
    ) {
        val timePickerState = rememberTimePickerState()
        var showTimePicker by remember { mutableStateOf(false) }

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
                onClick = {
                    showTimePicker = true
                }
            ) {
                if(showTimePicker) {
                    TimePickerDialog(
                        showTimePickerChange = {
                            showTimePicker = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TimePickerDialog(showTimePickerChange: () -> Unit) {
    Dialog(
        onDismissRequest = {
            showTimePickerChange()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = PurpleGrey80
                ),
            shape = RoundedCornerShape(10.dp),
            tonalElevation = 6.dp,
            color = PurpleGrey80
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {

                WheelTimePicker(
                    modifier = Modifier.padding(15.dp).align(Alignment.CenterHorizontally),
                    timeFormat = TimeFormat.AM_PM
                ) { snappedTime ->
                    println(snappedTime)
                }

                /**
                 * 취소, 확인 버튼
                 */
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.padding(end = 10.dp),
                        shape = RoundedCornerShape(0.dp),
                        onClick = {}
                    ) {
                        Text(
                            text = stringResource(id = R.string.cancel)
                        )
                    }

                    Button(
                        shape = RoundedCornerShape(0.dp),
                        onClick = {}
                    ) {
                        Text(
                            text = stringResource(id = R.string.confirm)
                        )
                    }
                }
            }
        }
    }
}