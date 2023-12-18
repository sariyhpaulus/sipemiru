package com.polstat.sipemiru.ui.auth

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.polstat.sipemiru.ui.profile.EditProfileReport
import com.polstat.sipemiru.ui.screen.Screen
import com.polstat.sipemiru.ui.state.EmailState
import com.polstat.sipemiru.ui.state.PasswordState
import com.polstat.sipemiru.ui.theme.Base
import com.polstat.sipemiru.ui.theme.Blue80
import com.polstat.sipemiru.ui.theme.PoppinsFamily
import com.polstat.sipemiru.ui.theme.SipemiruTheme
import com.polstat.sipemiru.ui.theme.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = viewModel(factory = RegisterViewModel.Factory),
    navController: NavController
){
    val coroutineScope = rememberCoroutineScope()
    val showToast = remember { mutableStateOf(false) }

    val currentScreen = mutableStateOf<Screen>(Screen.Register)

    if(showToast.value){
        Toast.makeText(LocalContext.current, "Register Success", Toast.LENGTH_SHORT).show()
        showToast.value = false
    }

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
                    text = "Register",
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
                        value = registerViewModel.firstNameField,
                        onValueChange = {
                            registerViewModel.updateFirstNameField(it)
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
                        value = registerViewModel.lastNameField,
                        onValueChange = {
                            registerViewModel.updateLastNameField(it)
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
                        value = registerViewModel.emailField,
                        onValueChange = {
                            registerViewModel.updateEmailField(it)
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
                    Spacer(modifier = Modifier.height(15.dp))
                    OutlinedTextField(
                        value = registerViewModel.passwordField,
                        onValueChange = {
                            registerViewModel.updatePasswordField(it)
                        },
                        label = {
                            Text(
                                text = "Password",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Password
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.85f),
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    OutlinedTextField(
                        value = registerViewModel.confirmPasswordField,
                        onValueChange = {
                            registerViewModel.updateConfirmPasswordField(it)
                        },
                        label = {
                            Text(
                                text = "Konfirmasi Password",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Password
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.85f),
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    ElevatedButton(
                        onClick = {
                            coroutineScope.launch {
                                when(registerViewModel.register()){
                                    RegisterResult.Success -> {
                                        showToast.value = true
                                    }
                                    RegisterResult.EmptyField -> {
                                        showToast.value = true
                                    }
                                    else -> {
                                        showToast.value = true
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
                            text = "Register",
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreenPreview() {
    SipemiruTheme {
        RegisterScreen(
            navController = NavController(LocalContext.current)
        )
    }
}