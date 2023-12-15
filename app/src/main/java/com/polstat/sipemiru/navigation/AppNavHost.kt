package com.polstat.sipemiru.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.polstat.sipemiru.ui.auth.LoginScreen
import com.polstat.sipemiru.ui.auth.LoginSuccessScreen
import com.polstat.sipemiru.ui.auth.LoginViewModel

@Composable
fun AppNavHost() {
    val loginViewModel = LoginViewModel()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(loginViewModel, navController) }
        composable("success") { LoginSuccessScreen(loginViewModel, navController) }
//        composable("beranda") { BerandaScreen(navController)}
//        composable("pengaduan") { PengaduanScreen(navController) }
//        composable("notifikasi") { NotifikasiScreen(navController) }
//        composable("aset") { AsetScreen(navController) }
//        composable("profil") { ProfilScreen(navController)}
//        composable("failed") { LoginFailedScreen(loginViewModel) }
    }
}