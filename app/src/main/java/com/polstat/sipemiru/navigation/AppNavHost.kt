package com.polstat.sipemiru.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.polstat.sipemiru.data.RuanganRepository
import com.polstat.sipemiru.service.SessionManager
import com.polstat.sipemiru.ui.auth.LoginScreen
import com.polstat.sipemiru.ui.auth.LoginSuccessScreen
import com.polstat.sipemiru.ui.auth.LoginViewModel
import com.polstat.sipemiru.ui.profile.ProfileScreen
import com.polstat.sipemiru.ui.ruangan.CreateRuanganScreen
import com.polstat.sipemiru.ui.ruangan.RuanganScreen
import com.polstat.sipemiru.ui.ruangan.RuanganViewModel
import com.polstat.sipemiru.ui.screen.HomeScreen

@Composable
fun AppNavHost() {
    val loginViewModel = LoginViewModel()
    val navController = rememberNavController()
    val ruanganViewModel = viewModel<RuanganViewModel>() // Gunakan viewModel() untuk membuat instance RuanganViewModel

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(loginViewModel, navController) }
        composable("success") { LoginSuccessScreen(loginViewModel, navController) }
        composable("home") { HomeScreen(navController) }
        composable("ruangan") { CreateRuanganScreen(ruanganViewModel, navController) } // Gunakan ruanganViewModel langsung
        composable("profile") { ProfileScreen(navController) }
    }
}
