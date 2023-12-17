package com.polstat.sipemiru.navigation

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.polstat.sipemiru.ui.peminjaman.AddPeminjamanScreen
import com.polstat.sipemiru.ui.peminjaman.PeminjamanViewModel
import com.polstat.sipemiru.ui.profile.ProfileScreen
import com.polstat.sipemiru.ui.ruangan.CreateRuanganScreen
import com.polstat.sipemiru.ui.ruangan.RuanganScreen
import com.polstat.sipemiru.ui.ruangan.RuanganViewModel
import com.polstat.sipemiru.ui.screen.HomeScreen
import com.polstat.sipemiru.ui.screen.PeminjamanRuanganScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost() {
    //val loginViewModel = viewModel<LoginViewModel>() // Gunakan viewModel() untuk membuat instance LoginViewModel
    val navController = rememberNavController()
    //val ruanganViewModel = viewModel<RuanganViewModel>() // Gunakan viewModel() untuk membuat instance RuanganViewModel
    val peminjamanViewModel = viewModel<PeminjamanViewModel>() // Gunakan viewModel() untuk membuat instance PeminjamanViewModel

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(viewModel(factory = LoginViewModel.Factory), navController) }
        //composable("success") { LoginSuccessScreen(loginViewModel, navController) }
        composable("home") { HomeScreen(navController) }
        composable("ruangan") { CreateRuanganScreen(viewModel(factory = RuanganViewModel.Factory), navController) } // Gunakan ruanganViewModel langsung
        composable("peminjaman"){ AddPeminjamanScreen(peminjamanViewModel, navController)}
        composable("profile") { ProfileScreen(navController) }
    }
}
