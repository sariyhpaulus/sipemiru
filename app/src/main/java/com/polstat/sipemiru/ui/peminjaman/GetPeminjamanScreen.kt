package com.polstat.sipemiru.ui.peminjaman

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.polstat.sipemiru.ui.component.PeminjamanItemCard
import com.polstat.sipemiru.ui.component.RuanganItemCard
import com.polstat.sipemiru.ui.screen.Screen
import com.polstat.sipemiru.ui.theme.Blue40
import com.polstat.sipemiru.ui.theme.Blue80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetPeminjamanScreen(getPeminjamanViewModel: GetPeminjamanViewModel, navController: NavController) {
    val currentScreen = mutableStateOf<Screen>(Screen.DaftarPeminjaman)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Daftar Peminjaman", color = Blue80, fontWeight = FontWeight.SemiBold)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Blue80)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp)
            )
        },
        content = { innerPadding->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(color = Blue40)
            ){
                when(getPeminjamanViewModel.daftarPeminjamanUiState){
                    is DaftarPeminjamanUiState.Loading -> {
                        Text(text = "Loading")
                        println("Error")
                    }
                    is DaftarPeminjamanUiState.Error -> {
                        Text(text = "Error")
                        println("Loading")
                    }
                    is DaftarPeminjamanUiState.Success -> {
                        println("Success")
                        val daftarPeminjaman = (getPeminjamanViewModel.daftarPeminjamanUiState as DaftarPeminjamanUiState.Success).listPeminjaman
                        println(daftarPeminjaman.size)
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 8.dp, start = 12.dp, end = 12.dp),
                            contentPadding = PaddingValues(8.dp),
                        ){
                            items(items = daftarPeminjaman){
                                PeminjamanItemCard(
                                    namaRuangan = it.ruangan.namaRuangan,
                                    tanggalPeminjaman = it.tanggalPeminjaman,
                                    waktuMulai = it.waktuMulai,
                                    waktuSelesai = it.waktuSelesai,
                                    status = it.status
                                )
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {}
    )

}