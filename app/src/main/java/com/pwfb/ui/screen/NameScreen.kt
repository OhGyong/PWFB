package com.pwfb.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pwfb.R
import com.pwfb.common.DataStoreResult.SET_NAME
import com.pwfb.common.ScreenName.SCREEN_WEIGHT
import com.pwfb.theme.Black
import com.pwfb.theme.DataStoreTheme
import com.pwfb.theme.Gray
import com.pwfb.theme.SettingTextFieldTypography
import com.pwfb.theme.SettingTitleTypography
import com.pwfb.theme.White
import com.pwfb.ui.viewmodel.NameViewModel

@Composable
fun NameScreen(
    navController: NavHostController,
    nameViewModel: NameViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            style = SettingTitleTypography.bodyLarge
        )

        var name by remember { mutableStateOf("") }

        LaunchedEffect(nameViewModel.nameObserve) {->
            if(nameViewModel.nameObserve == SET_NAME) {
                navController.navigate(SCREEN_WEIGHT)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            SetNameView(
                name,
                onNameChange= {newName->
                    name=newName
                }
            )
        }

        ButtonView(
            textValue = name,
            saveValue = {nameViewModel.setName(name)}
        )
    }
}

@Composable
fun SetNameView(name: String, onNameChange:(String)->Unit) {
    TextField(
        modifier = Modifier
            .wrapContentWidth()
            .background(Black),
        value = name,
        textStyle = SettingTextFieldTypography.bodyLarge,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = White,
            backgroundColor = Color.Transparent, // 밑줄을 제거하려면 배경색을 투명으로 지정
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.input_name),
                color = Gray,
                style = SettingTextFieldTypography.labelLarge
            )
        },
        onValueChange = {
            onNameChange(it)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NamePreview() {
    DataStoreTheme {
        val navController = rememberNavController()
        NameScreen(navController = navController)
    }
}