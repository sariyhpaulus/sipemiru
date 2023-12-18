package com.polstat.sipemiru.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.polstat.sipemiru.repository.UserState
import com.polstat.sipemiru.ui.component.CustomBottomNavigation
import com.polstat.sipemiru.ui.ruangan.GetRuanganScreen
import com.polstat.sipemiru.ui.ruangan.GetRuanganViewModel
import com.polstat.sipemiru.ui.screen.Screen
import com.polstat.sipemiru.ui.theme.Blue40
import com.polstat.sipemiru.ui.theme.Blue60
import com.polstat.sipemiru.ui.theme.PoppinsFamily
import com.polstat.sipemiru.ui.theme.SipemiruTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel, navController: NavController){
    val currentScreen = mutableStateOf<Screen>(Screen.Profile)
    //lateinit var userState: UserState

    Scaffold(
        topBar = {},
        bottomBar = {
            CustomBottomNavigation(
                navController = navController,
                currentScreenId = Screen.Profile.id
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
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    tint = Blue40,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(80.dp)
                        .align(Alignment.Center)
                )
            }

            Card(
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        top = 50.dp, // Add margin to the top
                        end = 10.dp,
                        bottom = 10.dp
                    )
                    .shadow(8.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.Gray,
                ),
                border = BorderStroke(1.dp, Color.White),
                shape = MaterialTheme.shapes.small
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    when(profileViewModel.profileUiState){
                        is ProfileUiState.Error -> {
                            Text(text = "Error")
                            println("Error")
                        }
                        is ProfileUiState.Loading -> {
                            Text(text = "Loading")
                            println("Loading")
                        }
                        is ProfileUiState.Success -> {
                            println("Success")
                            val profile = (profileViewModel.profileUiState as ProfileUiState.Success).userResponse
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "First Name",
                                    fontFamily = PoppinsFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = profile.data?.firstName ?: "",
                                    fontFamily = PoppinsFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black,
                                    fontSize = 16.sp
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Last Name",
                                    fontFamily = PoppinsFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = profile.data?.lastName ?: "",
                                    fontFamily = PoppinsFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black,
                                    fontSize = 16.sp
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Email",
                                    fontFamily = PoppinsFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = profile.data?.email ?: "",
                                    fontFamily = PoppinsFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black,
                                    fontSize = 16.sp
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Role",
                                    fontFamily = PoppinsFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black,
                                    fontSize = 16.sp
                                )
                                if(profile.data?.roles.toString() == "[ROLE_ADMIN]"){
                                    Text(
                                        text = "ADMIN",
                                        fontFamily = PoppinsFamily,
                                        fontWeight = FontWeight.Medium,
                                        color = Color.Black,
                                        fontSize = 16.sp
                                    )
                                } else{
                                    Text(
                                        text = "PEMINJAM",
                                        fontFamily = PoppinsFamily,
                                        fontWeight = FontWeight.Medium,
                                        color = Color.Black,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }

                }
            }

            ElevatedButton(
                onClick = {
                    navController.navigate(Screen.EditProfile.id) },
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
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
                Text("Edit Profile")
            }

            ElevatedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
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
                Text("Change Password")
            }


            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Blue60
                ),
            ){
                Text("Logout")
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    SipemiruTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            ProfileScreen(viewModel(factory = ProfileViewModel.Factory), navController)
        }
    }
}