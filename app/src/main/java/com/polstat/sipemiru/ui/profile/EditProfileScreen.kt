package com.polstat.sipemiru.ui.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.polstat.sipemiru.ui.auth.EmailTextField
import com.polstat.sipemiru.ui.auth.LoginButton
import com.polstat.sipemiru.ui.auth.LoginPicture
import com.polstat.sipemiru.ui.auth.LoginTitle
import com.polstat.sipemiru.ui.auth.LoginViewModel
import com.polstat.sipemiru.ui.auth.PasswordTextField
import com.polstat.sipemiru.ui.screen.Screen
import com.polstat.sipemiru.ui.state.EmailState
import com.polstat.sipemiru.ui.state.PasswordState
import com.polstat.sipemiru.ui.theme.Base
import com.polstat.sipemiru.ui.theme.Blue80
import com.polstat.sipemiru.ui.theme.PoppinsFamily
import com.polstat.sipemiru.ui.theme.Typography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun EditProfileScreen(
    editProfileViewModel: EditProfileViewModel,
    navController: NavController
){
    //val currentScreen = mutableStateOf<Screen>(Screen.Profile)
    val emailState = mutableStateOf(TextFieldValue(""))
    //val passwordState = remember { PasswordState() }
    val firstName = mutableStateOf(TextFieldValue(""))
    val lastName = mutableStateOf(TextFieldValue(""))


    //val loginResponse by loginViewModel.loginResponse.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    val showToast = remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Blue80),
        verticalArrangement = Arrangement.Bottom
    ){
        item {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.85f)
                    .fillMaxWidth()
                    .background(
                        color = Base,
                        shape = RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 50.dp
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Edit Profile",
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth(0.85f),
                    style = Typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .height(8.dp)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                ){}
                Spacer(modifier = Modifier.height(15.dp))
                Column {
                    OutlinedTextField(
                        value = firstName.value,
                        onValueChange = {
                            firstName.value = it
                            editProfileViewModel.onFirstNameChange(it.text)
                        },
                        label = {
                            Text(
                                text = "First Name",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.85f),
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    OutlinedTextField(
                        value = lastName.value,
                        onValueChange = {
                            lastName.value = it
                            editProfileViewModel.onLastNameChange(it.text)
                        },
                        label = {
                            Text(
                                text = "Last Name",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.85f),
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    OutlinedTextField(
                        value = emailState.value,
                        onValueChange = {
                            emailState.value = it
                            editProfileViewModel.onEmailChange(it.text)
                        },
                        label = {
                            Text(
                                text = "Email",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.85f),
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    ElevatedButton(
                        onClick = {
                            coroutineScope.launch {
                                when(editProfileViewModel.editProfile()){
                                    EditProfileReport.ERROR -> {
                                        showToast.value = true
                                    }
                                    EditProfileReport.SUCCESS -> {
                                        navController.navigate(Screen.Profile.id)
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.85f),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Blue80,
                            contentColor = Color.White
                        ),
                    ) {
                        Text(
                            text = "Ubah Profil",
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = PoppinsFamily,
                            fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}