package com.polstat.sipemiru.ui.ruangan

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
import com.polstat.sipemiru.ui.component.CustomBottomNavigation
import com.polstat.sipemiru.ui.screen.Screen
import com.polstat.sipemiru.ui.theme.SipemiruTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RuanganScreen(navController: NavController){
    val currentScreen = mutableStateOf<Screen>(Screen.Ruangan)

    Scaffold(
        topBar = {},
        bottomBar = {
            CustomBottomNavigation(
                navController = navController,
                currentScreenId = Screen.Ruangan.id
            ){
                currentScreen.value = it
                navController.navigate(it.id)
            }
        }
    ) { innerPadding->
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
fun RuanganScreenPreview() {
    SipemiruTheme {
        val navController = rememberNavController()
        RuanganScreen(navController = navController)
    }
}