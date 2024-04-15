package com.pwfb.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pwfb.common.ScreenName.SCREEN_DAY
import com.pwfb.common.ScreenName.SCREEN_WEIGHT
import com.pwfb.ui.viewmodel.DayViewModel

@Composable
fun DayScreen(
    navController: NavHostController,
    dayViewModel: DayViewModel = hiltViewModel()
) {
    BackHandler(enabled = true) {
        navController.navigate(SCREEN_WEIGHT) {
            popUpTo(SCREEN_DAY) { inclusive = true }
        }
    }

}
