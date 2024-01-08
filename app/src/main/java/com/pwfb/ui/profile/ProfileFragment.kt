package com.pwfb.ui.profile

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
import com.pwfb.R
import com.pwfb.base.BaseFragment
import com.pwfb.common.DataStoreResult
import com.pwfb.databinding.FragmentProfileBinding
import com.pwfb.ui.MainViewModel
import com.pwfb.util.ClearDecorator
import com.pwfb.util.DayDisableDecorator
import com.pwfb.util.SelectDecorator
import com.pwfb.util.TodayDecorator
import dagger.hilt.android.AndroidEntryPoint
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar


@AndroidEntryPoint
@SuppressLint("SimpleDateFormat", "SetTextI18n", "InflateParams")
class ProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: MainViewModel by viewModels()

    private var dDayPref = ""
    private var datePref = ""
    private var timePref = ""
    private var weightPref = 0.0
    private var weight = 0.0

    private var bottomSheetView: View? = null
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        setOnClickListener()
        setCalendarView()
        viewModelObserve()
        viewModel.getName()
        viewModel.getWeight()
        viewModel.getDDay()

        bottomSheetDialog = BottomSheetDialog(requireContext())

        return binding.root
    }

    private fun setOnClickListener() {
        binding.btModify.setOnClickListener {
            viewModel.setDDay("$dDayPref $timePref")
            viewModel.setWeight(weight.toString())
        }

        binding.btTime.setOnClickListener {
            setTimeSpinner()
        }

        binding.ivMinus.setOnClickListener {
            weight = (weight*10 - 1).toInt() / 10.0
            binding.tvWeight.text = weight.toString()+"Kg"
            setWeightBtn()
        }

        binding.ivPlus.setOnClickListener {
            weight = (weight*10 + 1).toInt() / 10.0
            binding.tvWeight.text = weight.toString()+"Kg"
            setWeightBtn()
        }

        binding.clPwfbInformation.setOnClickListener {
            bottomSheetView = layoutInflater.inflate(R.layout.dialog_info, null)
            bottomSheetDialog.setContentView(bottomSheetView!!)
            bottomSheetDialog.show()
        }

        binding.clTheDayStrategy.setOnClickListener {
            val bottomSheet = StrategyDialogFragment()

            bottomSheet.show(activity?.supportFragmentManager!!, bottomSheet.tag)
        }
    }

    private fun viewModelObserve() {
        viewModel.nameObserve.observe(viewLifecycleOwner) {
            binding.tvName.text = it
        }

        viewModel.weightObserve.observe(viewLifecycleOwner) {
            if(it == DataStoreResult.SET_WEIGHT) {
                binding.btModify.isEnabled = false
                binding.btModify.setTextColor(requireContext().getColor(R.color.c_949292))
                viewModel.getWeight()
                return@observe
            }

            weightPref = it.toDouble()
            weight = it.toDouble()

            binding.tvIdealWeight.text = getString(
                R.string.profile_weight,
                (weight*0.99).toString(),
                weight.toString()
            )

            binding.tvWeight.text = weight.toString()+"Kg"
        }

        viewModel.dDayObserve.observe(viewLifecycleOwner) {
            if(it == DataStoreResult.SET_D_DAY) {
                binding.btModify.isEnabled = false
                binding.btModify.setTextColor(requireContext().getColor(R.color.c_949292))
                return@observe
            } else {
                dDayPref = it
                datePref = it.substring(0..9)
                setDDay()

                setTime(it.substring(14..18))

                val targetCalendarDay = CalendarDay.from(org.threeten.bp.LocalDate.parse(
                    datePref,
                    DateTimeFormatter.ofPattern("yyyy.MM.dd"))
                )
                val targetDecorator = SelectDecorator(
                    requireContext().getColor(R.color.c_caab3f),
                    targetCalendarDay
                )
                binding.cvCalendar.selectedDate = targetCalendarDay
                binding.cvCalendar.addDecorator(targetDecorator)
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

            calendarHeaderBuilder
                .append(calendarHeaderElements[0]).append("년 ")
                .append(calendarHeaderElements[1]).append("월")

            calendarHeaderBuilder.toString()
        }

        val today = CalendarDay.today()
        val disabledDates = hashSetOf<CalendarDay>()

        val dayDisableDecorator = DayDisableDecorator(disabledDates, today, requireContext().getColor(R.color.c_949292))
        val todayDecorator = TodayDecorator(requireContext().getColor(R.color.c_caab3f))

        binding.cvCalendar.addDecorators(dayDisableDecorator, todayDecorator)

        // 날짜 선택 시 처리
        binding.cvCalendar.setOnDateChangedListener { _, date, _ ->
            binding.cvCalendar.addDecorators(
                ClearDecorator(requireContext().getColor(R.color.white), date),
                dayDisableDecorator, todayDecorator,
                SelectDecorator(requireContext().getColor(R.color.c_caab3f), date)
            )

            binding.btModify.isEnabled = true
            binding.btModify.setTextColor(requireContext().getColor(R.color.white))

            val month = if(date.month<10) "0${date.month}" else "${date.month}"
            val day = if(date.day<10) "0${date.day}" else "${date.day}"

            dDayPref = "${date.year}.$month.$day${getWeek(date)}"

            setDDay()
        }
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

    private fun setTime(time: String) {
        val hourPref = time.substring(0..1)
        val minutePref = time.substring(3..4)

        val amPm = if(hourPref[0]=='1' && hourPref[1].code>2) "오후" else "오전"
        val hour = if(amPm == "오후") (hourPref.toInt()-12).toString() else hourPref

        binding.btTime.text = "$amPm $hour:$minutePref"
        timePref = "$hourPref:$minutePref⏳"
    }

    private fun setDDay() {
        val targetDate = SimpleDateFormat("yyyy.MM.dd").parse(datePref)!!.time
        val today = Calendar.getInstance().time.time

        val dDay = (today - targetDate) / (60*60*24*1000)
        binding.tvDDay.text = "D$dDay"
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

            binding.btTime.text = "$amPm $hour:$minute"
            timePref = "$hour:$minute⏳"
        }

        val timePickerDialog = TimePickerDialog(
            requireContext(),
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

    private fun setWeightBtn() {
        if(weightPref != weight) {
            binding.btModify.isEnabled = true
            binding.btModify.setTextColor(requireContext().getColor(R.color.white))
        } else {
            binding.btModify.isEnabled = false
            binding.btModify.setTextColor(requireContext().getColor(R.color.c_949292))
        }
    }
}