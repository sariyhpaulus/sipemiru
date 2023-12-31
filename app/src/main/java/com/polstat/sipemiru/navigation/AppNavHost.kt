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
import com.polstat.sipemiru.ui.auth.RegisterScreen
import com.polstat.sipemiru.ui.auth.RegisterViewModel
import com.polstat.sipemiru.ui.peminjaman.AddPeminjamanScreen
import com.polstat.sipemiru.ui.peminjaman.AddPeminjamanViewModel
import com.polstat.sipemiru.ui.peminjaman.GetPeminjamanScreen
import com.polstat.sipemiru.ui.peminjaman.GetPeminjamanViewModel
import com.polstat.sipemiru.ui.profile.EditProfileScreen
import com.polstat.sipemiru.ui.profile.EditProfileViewModel
import com.polstat.sipemiru.ui.profile.ProfileScreen
import com.polstat.sipemiru.ui.profile.ProfileViewModel
import com.polstat.sipemiru.ui.ruangan.CreateRuanganScreen
import com.polstat.sipemiru.ui.ruangan.GetRuanganScreen
import com.polstat.sipemiru.ui.ruangan.GetRuanganViewModel
import com.polstat.sipemiru.ui.ruangan.RuanganViewModel
import com.polstat.sipemiru.ui.screen.HomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(viewModel(factory = LoginViewModel.Factory), navController) }
        composable("register") { RegisterScreen(viewModel(factory = RegisterViewModel.Factory), navController) }
        composable("ruangan") { CreateRuanganScreen(viewModel(factory = RuanganViewModel.Factory), navController) }
        composable("peminjaman"){ AddPeminjamanScreen(viewModel(factory = AddPeminjamanViewModel.Factory), navController)}
        composable("profile") { ProfileScreen(viewModel(factory = ProfileViewModel.Factory),navController)}
        composable("editProfile") { EditProfileScreen(viewModel(factory = EditProfileViewModel.Factory), navController) }
        composable("daftarRuangan") { GetRuanganScreen(viewModel(factory = GetRuanganViewModel.Factory), navController)}
        composable("daftarPeminjaman") { GetPeminjamanScreen(viewModel(factory = GetPeminjamanViewModel.Factory), navController)}
    }
}
