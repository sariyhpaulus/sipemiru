package com.polstat.sipemiru.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MeetingRoom
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val id : String,
    val title: String,
    val icon: ImageVector
) {
    object Beranda : Screen("home", "Beranda", Icons.Outlined.Home)
    object Peminjaman : Screen("peminjaman", "Peminjaman", Icons.Outlined.EditNote)
    object DaftarPeminjaman : Screen("daftarPeminjaman", "Daftar Peminjaman", Icons.Outlined.EditNote)
    object Ruangan : Screen("ruangan", "Ruangan", Icons.Outlined.MeetingRoom)
    object DaftarRuangan : Screen("daftarRuangan", "Daftar Ruangan", Icons.Outlined.MeetingRoom)
    object View : Screen("view", "View", Icons.Outlined.Assignment)
    object Profile : Screen("profile", "Profile", Icons.Outlined.Person)
    object EditProfile : Screen("editProfile", "Edit Profile", Icons.Outlined.Person)

    object ItemsUser{
        val list = listOf(DaftarPeminjaman, DaftarRuangan, Profile)
    }

    object ItemsAdmin{
        val list = listOf(Peminjaman, DaftarPeminjaman, Ruangan, Profile)
    }
}