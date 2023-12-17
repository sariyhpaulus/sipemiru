package com.polstat.sipemiru.ui.ruangan

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.polstat.sipemiru.ui.component.CustomBottomNavigation
import com.polstat.sipemiru.ui.screen.Screen
import com.polstat.sipemiru.ui.theme.Base
import com.polstat.sipemiru.ui.theme.Blue60
import com.polstat.sipemiru.ui.theme.SipemiruTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CreateRuanganScreen(ruanganViewModel: RuanganViewModel ,navController: NavController){
    val currentScreen = mutableStateOf<Screen>(Screen.Ruangan)
    val ruanganId = mutableStateOf(TextFieldValue(""))
    val namaRuangan = mutableStateOf(TextFieldValue(""))
    //val gedung = mutableStateOf("")
    //val lantai = mutableStateOf("")
    val kapasitas = mutableStateOf(TextFieldValue(""))

    // List of items for the dropdown
    val gedungs = listOf("Gedung 1", "Gedung 2", "Gedung 3")
    val lantais = listOf("Lantai 1", "Lantai 2", "Lantai 3", "Lantai 4", "Lantai 5", "Lantai 6")

    // State to hold the selected item
    var selectedGedung by remember { mutableStateOf(gedungs[2]) }
    var selectedLantai by remember { mutableStateOf(lantais[2]) }

    // State to track whether the dropdown is expanded or not
    var expandedGedung by remember { mutableStateOf(false) }
    var expandedLantai by remember { mutableStateOf(false) }

    val ruanganResponse by ruanganViewModel.ruanganResponse.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    val showToast = remember{
        mutableStateOf(false)
    }

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
                .background(color = Base)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ){
                Text(
                    text = "Tambah Ruangan",
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
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                    ,
                    singleLine = true
                )

                Text(
                    text = "Nama Ruangan",
                    fontWeight = FontWeight.SemiBold,
                )
                TextField(
                    value = namaRuangan.value,
                    onValueChange = {
                        namaRuangan.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    singleLine = true
                )

                Text(
                    text = "Gedung",
                    fontWeight = FontWeight.SemiBold,
                )
                ExposedDropdownMenuBox(
                    expanded = expandedGedung,
                    onExpandedChange = { newValue -> expandedGedung = newValue}
                ) {
                    TextField(
                        value = selectedGedung,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedGedung)
                        },
                        modifier = Modifier.menuAnchor().fillMaxWidth().padding(bottom = 8.dp),
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedGedung,
                        onDismissRequest = { expandedGedung = false }
                    ) {
                        gedungs.forEachIndexed { index, item ->
                            DropdownMenuItem(onClick = {
                                selectedGedung = item
                                expandedGedung = false
                            }) {
                                Text(
                                    text = item,
                                    modifier = Modifier.padding(8.dp),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }

                Text(
                    text = "Lantai",
                    fontWeight = FontWeight.SemiBold,
                )
                ExposedDropdownMenuBox(
                    expanded = expandedLantai,
                    onExpandedChange = { newValue -> expandedLantai = newValue}
                ) {
                    TextField(
                        value = selectedLantai,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedLantai)
                        },
                        modifier = Modifier.menuAnchor().fillMaxWidth().padding(bottom = 8.dp),
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedLantai,
                        onDismissRequest = { expandedLantai = false }
                    ) {
                        lantais.forEachIndexed { index, item ->
                            DropdownMenuItem(onClick = {
                                selectedLantai = item
                                expandedLantai = false
                            }) {
                                Text(
                                    text = item,
                                    modifier = Modifier.padding(8.dp),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }

                Text(
                    text = "Kapasitas Ruangan",
                    fontWeight = FontWeight.SemiBold,
                )
                TextField(
                    value = kapasitas.value,
                    onValueChange = {
                        kapasitas.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    singleLine = true
                )

                ElevatedButton(
                    onClick = {
                        coroutineScope.launch {
                            ruanganViewModel.requestRuangan(
                                ruanganId = ruanganId.value.text,
                                namaRuangan = namaRuangan.value.text,
                                gedung = selectedGedung,
                                lantai = selectedLantai,
                                kapasitas = kapasitas.value.text
                            )
                            delay(500)
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
                    Text("Submit")
                    ruanganResponse?.let {
                        if (it.data != null) {
                            navController.navigate(Screen.Ruangan.id)
                        } else{
                            showToast.value = true
                        }
                    }
                }

                if (showToast.value) {
                    Toast.makeText(LocalContext.current, "${ruanganResponse?.message}" + ": Ruangan tidak valid", Toast.LENGTH_SHORT).show()
                    showToast.value = false // Reset the state
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateRuanganScreenPreview() {
    SipemiruTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val ruanganViewModel = RuanganViewModel()
            val navController = rememberNavController()
            CreateRuanganScreen(ruanganViewModel = ruanganViewModel, navController = navController)
        }
    }
}