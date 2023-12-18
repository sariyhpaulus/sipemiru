package com.polstat.sipemiru.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.polstat.sipemiru.ui.auth.LoginScreen
import com.polstat.sipemiru.ui.auth.LoginViewModel
import com.polstat.sipemiru.ui.peminjaman.AddPeminjamanScreen
import com.polstat.sipemiru.ui.peminjaman.AddPeminjamanViewModel
import com.polstat.sipemiru.ui.profile.ProfileScreen
import com.polstat.sipemiru.ui.ruangan.CreateRuanganScreen
import com.polstat.sipemiru.ui.ruangan.GetRuanganScreen
import com.polstat.sipemiru.ui.ruangan.GetRuanganViewModel
import com.polstat.sipemiru.ui.ruangan.RuanganViewModel
import com.polstat.sipemiru.ui.screen.HomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost() {
    //val loginViewModel = viewModel<LoginViewModel>() // Gunakan viewModel() untuk membuat instance LoginViewModel
    val navController = rememberNavController()
    //val ruanganViewModel = viewModel<RuanganViewModel>() // Gunakan viewModel() untuk membuat instance RuanganViewModel
    //val addPeminjamanViewModel = viewModel<AddPeminjamanViewModel>() // Gunakan viewModel() untuk membuat instance PeminjamanViewModel

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(viewModel(factory = LoginViewModel.Factory), navController) }
        //composable("success") { LoginSuccessScreen(loginViewModel, navController) }
        composable("home") { HomeScreen(navController) }
        composable("ruangan") { CreateRuanganScreen(viewModel(factory = RuanganViewModel.Factory), navController) } // Gunakan ruanganViewModel langsung
        composable("peminjaman"){ AddPeminjamanScreen(viewModel(factory = AddPeminjamanViewModel.Factory), navController)}
        composable("profile") { ProfileScreen(navController) }
        composable("daftarRuangan") { GetRuanganScreen(viewModel(factory = GetRuanganViewModel.Factory), navController)}
    }
}
