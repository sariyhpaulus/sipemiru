package com.polstat.sipemiru.ui.ruangan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.polstat.sipemiru.ui.component.RuanganItemCard
import com.polstat.sipemiru.ui.screen.Screen
import com.polstat.sipemiru.ui.theme.Base
import com.polstat.sipemiru.ui.theme.Blue40
import com.polstat.sipemiru.ui.theme.Blue60
import com.polstat.sipemiru.ui.theme.Blue80
import com.polstat.sipemiru.ui.theme.SipemiruTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetRuanganScreen(getRuanganViewModel: GetRuanganViewModel, navController: NavController){
    val currentScreen = mutableStateOf<Screen>(Screen.DaftarRuangan)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Daftar Ruangan", color = Blue80, fontWeight = FontWeight.SemiBold)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Blue80)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth().padding(bottom = 0.dp)
            )
        },
        content = { innerPadding-> PaddingValues(0.dp)
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(color = Blue40)
            ) {
                when(getRuanganViewModel.daftarRuanganUiState){
                    is DaftarRuanganUiState.Error -> {
                        Text(text = "Error")
                        println("Error")
                    }
                    is DaftarRuanganUiState.Loading -> {
                        Text(text = "Loading")
                        println("Loading")
                    }
                    is DaftarRuanganUiState.Success -> {
                        println("Success")
                        val daftarRuangan = (getRuanganViewModel.daftarRuanganUiState as DaftarRuanganUiState.Success).listRuangan
                        println(daftarRuangan.size)
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize().fillMaxWidth()
                                .padding(top = 8.dp, bottom = 8.dp, start = 12.dp, end = 12.dp),
                            contentPadding = PaddingValues(8.dp),
                        ) {
                            items(items = daftarRuangan, key = {ruangan -> ruangan.ruanganId}){
                                RuanganItemCard(
                                    ruanganId = it.ruanganId,
                                    namaRuangan = it.namaRuangan,
                                    gedung = it.gedung,
                                    lantai = it.lantai,
                                    kapasitas = it.kapasitas
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

@Preview
@Composable
fun GetRuanganScreenPreview() {
    SipemiruTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            GetRuanganScreen(viewModel(factory = GetRuanganViewModel.Factory), navController)
        }
    }
}