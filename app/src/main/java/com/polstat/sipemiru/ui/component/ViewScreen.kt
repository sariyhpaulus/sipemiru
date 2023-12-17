package com.polstat.sipemiru.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.polstat.sipemiru.ui.screen.Screen
import com.polstat.sipemiru.ui.theme.SipemiruTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewScreen(navController: NavController) {
    val currentScreen = mutableStateOf<Screen>(Screen.Beranda)

    Scaffold(
        topBar = {},
        bottomBar = {
            CustomBottomNavigation(
                navController = navController,
                currentScreenId = Screen.View.id
            ){
                currentScreen.value = it
                navController.navigate(it.id)
            }


        }
    ) {innerPadding->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    SipemiruTheme {
        val navController = rememberNavController()
        ViewScreen(navController = navController)
    }
}