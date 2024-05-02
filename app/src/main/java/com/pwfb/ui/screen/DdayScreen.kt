package com.pwfb.ui.screen

import android.graphics.drawable.Drawable
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
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
import com.pwfb.ui.viewmodel.DdayViewModel
import com.pwfb.util.ClearDecorator
import com.pwfb.util.DayDisableDecorator
import com.pwfb.util.SelectDecorator
import com.pwfb.util.TodayDecorator

@Composable
fun DdayScreen(
    navController: NavHostController,
    calendarDrawable: List<Drawable?>,
    dDayViewModel: DdayViewModel = hiltViewModel()
) {
    BackHandler(enabled = true) {
        navController.navigate(SCREEN_WEIGHT) {
            popUpTo(SCREEN_DAY) { inclusive = true }
        }
    }

    val datePrefState = remember { mutableStateOf("") }
    var timePrefState by remember { mutableStateOf(dDayViewModel.setCurrentTime()) }

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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            CalendarView(calendarDrawable, dDayViewModel, datePrefState)

            TimeView(
                dDayViewModel,
                timePrefState,
                setNewTime = { newTime ->
                    timePrefState = newTime
                }
            )
        }

        NextButtonView(
            isDday = true,
            textValue = datePrefState.value,
            onClick = {
                println("${datePrefState.value} $timePrefState")
                dDayViewModel.setDDay("${datePrefState.value} $timePrefState")
            }
        )
    }
}

@Composable
fun CalendarView(
    calendarDrawable: List<Drawable?>,
    dDayViewModel: DdayViewModel,
    datePrefState: MutableState<String>
) {
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

                datePrefState.value = dDayViewModel.setDate(date)
            }
        })
}

@Composable
fun TimeView(dDayViewModel: DdayViewModel, timePrefState: String, setNewTime: (String)->Unit) {
    Row(
        modifier = Modifier,
    ) {
        var isShowTimePicker by remember { mutableStateOf(false) }

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
                    isShowTimePicker = true
                }
            ) {

                Text(
                    text = timePrefState,
                )

                if(isShowTimePicker) {
                    TimePickerDialog(
                        timePrefState,
                        setNewTime,
                        dDayViewModel,
                        showTimePickerChange = {
                            isShowTimePicker = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TimePickerDialog(
    timePrefState: String,
    setNewTime: (String)->Unit,
    dDayViewModel: DdayViewModel,
    showTimePickerChange: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            showTimePickerChange()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(0.6f),
            shape = RoundedCornerShape(10.dp),
            tonalElevation = 5.dp,
            color = DarkGray
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {

                var updateTime = ""

                WheelTimePicker(
                    modifier = Modifier
                        .padding(15.dp)
                        .align(Alignment.CenterHorizontally),
                    timeFormat = TimeFormat.AM_PM,
                    // 날짜 선택 시에 대한 UI
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        color = DarkGray,
                        border = BorderStroke(2.dp, Yellow40)
                    )
                ) { snappedTime ->
                    updateTime = dDayViewModel.updateTime(snappedTime)
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
                        shape = RoundedCornerShape(0.dp),
                        colors =  ButtonDefaults.buttonColors(DarkGray),
                        onClick = {
                            showTimePickerChange()
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.cancel)
                        )
                    }

                    Button(
                        shape = RoundedCornerShape(0.dp),
                        colors =  ButtonDefaults.buttonColors(DarkGray),
                        onClick = {
                            setNewTime(updateTime)
                            println("timePrefState: $timePrefState   updateTime: $updateTime")
                            showTimePickerChange()

                        }
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