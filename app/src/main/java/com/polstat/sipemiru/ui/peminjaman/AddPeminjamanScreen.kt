package com.polstat.sipemiru.ui.peminjaman

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.polstat.sipemiru.ui.component.CustomBottomNavigation
import com.polstat.sipemiru.ui.component.DatePicker
import com.polstat.sipemiru.ui.component.TimePicker
import com.polstat.sipemiru.ui.ruangan.RuanganViewModel
import com.polstat.sipemiru.ui.screen.Screen
import com.polstat.sipemiru.ui.theme.Base
import com.polstat.sipemiru.ui.theme.Blue60
import com.polstat.sipemiru.ui.theme.SipemiruTheme
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import java.time.Clock
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPeminjamanScreen(addPeminjamanViewModel: AddPeminjamanViewModel, navController: NavController){
    val currentScreen = mutableStateOf<Screen>(Screen.Ruangan)

    val ruanganId = remember { mutableStateOf(TextFieldValue("")) }

    val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    val tanggalPeminjaman = remember { mutableStateOf(currentDate) }
    val waktuMulai = remember { mutableStateOf(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))) }
    val waktuSelesai = remember { mutableStateOf(LocalTime.now().plusHours(1).format(DateTimeFormatter.ofPattern("HH:mm"))) }


    val keperluan = remember { mutableStateOf(TextFieldValue("")) }

    val coroutineScope = rememberCoroutineScope()
    val showToast = remember{
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {},
        bottomBar = {
            CustomBottomNavigation(
                navController = navController,
                currentScreenId = Screen.Peminjaman.id
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
                .background(color = Base)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ){
                Text(
                    text = "Peminjaman Ruangan",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(16.dp))

                Text(
                    text = "Kode Ruangan",
                    fontWeight = FontWeight.SemiBold,
                )
                TextField(
                    value = ruanganId.value,
                    onValueChange = {
                        ruanganId.value = it
                        addPeminjamanViewModel.onRuanganIdChange(it.text)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                    ,
                    singleLine = true
                )

                Text(
                    text = "Tanggal Peminjaman",
                    fontWeight = FontWeight.SemiBold,
                )
                DatePicker(
                    value = tanggalPeminjaman.value,
                    onValueChange = {
                        tanggalPeminjaman.value = it
                        addPeminjamanViewModel.onTanggalPeminjamanChange(it)
                    }
                )

                Text(
                    text = "Waktu Mulai",
                    fontWeight = FontWeight.SemiBold,
                )
                TimePicker(
                    value = waktuMulai.value,
                    onValueChange = {
                        waktuMulai.value = it
                        addPeminjamanViewModel.onWaktuMulaiChange(it)
                    }
                )

                Text(
                    text = "Waktu Selesai",
                    fontWeight = FontWeight.SemiBold,
                )
                TimePicker(
                    value = waktuSelesai.value,
                    onValueChange = {
                        waktuSelesai.value = it
                        addPeminjamanViewModel.onWaktuSelesaiChange(it)
                    }
                )

                Text(
                    text = "Keperluan",
                    fontWeight = FontWeight.SemiBold,
                )
                TextField(
                    value = keperluan.value,
                    onValueChange = {
                        keperluan.value = it
                        addPeminjamanViewModel.onKeperluanChange(it.text)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                    ,
                    singleLine = true
                )

                ElevatedButton(
                    onClick = {
                        coroutineScope.launch {
                            when(addPeminjamanViewModel.addPeminjaman()){
                                AddPeminjamanReport.FAILED -> {
                                    showToast.value = true
                                }
                                AddPeminjamanReport.SUCCESS -> {
                                    navController.navigate(Screen.Peminjaman.id)
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            top = 40.dp,
                            end = 16.dp,
                            bottom = 8.dp
                        )
                        .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                        containerColor = Blue60,
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 15.dp
                        )
                    ) {
                    Text(
                        text = "Ajukan Peminjaman",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AddPeminjamanScreenPreview() {
    SipemiruTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            AddPeminjamanScreen(viewModel(factory = AddPeminjamanViewModel.Factory), navController = navController)
        }
    }
}
