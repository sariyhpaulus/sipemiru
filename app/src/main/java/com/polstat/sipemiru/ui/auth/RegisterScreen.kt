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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polstat.sipemiru.ui.state.EmailState
import com.polstat.sipemiru.ui.state.PasswordState
import com.polstat.sipemiru.ui.theme.Blue80
import com.polstat.sipemiru.ui.theme.SipemiruTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = viewModel(factory = RegisterViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val emailState = remember {
        EmailState()
    }
    val passwordState = remember {
        PasswordState()
    }
    val confirmPasswordState = remember {
        EmailState()
    }
    val showToast = remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Blue80
            ),
        verticalArrangement = Arrangement.Bottom
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.85f)
                    .fillMaxWidth()
                    .background(
                        color = LightGray,
                        shape = RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 50.dp
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .height(8.dp)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {}
                Column {
                    Spacer(modifier = Modifier.height(15.dp))
                    //LoginPicture()
                    Spacer(modifier = Modifier.height(15.dp))
                    LoginTitle()
                    Spacer(modifier = Modifier.height(15.dp))
                    EmailTextField(emailState)
                    Spacer(modifier = Modifier.height(5.dp))
                    PasswordTextField(passwordState)
                    Spacer(modifier = Modifier.height(15.dp))
//                    LoginButton(emailState, passwordState, loginViewModel) {
//                        loginResponse?.let {
//                            if (it.data != null){
//                                navController.navigate("beranda")
//                            } else {
//                                showToast.value = true
//                            }
//                        }
//                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    if (showToast.value) {
                        Toast.makeText(LocalContext.current, ": Email dan/atau password salah!", Toast.LENGTH_SHORT).show()
                        showToast.value = false // Reset the state
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreenPreview() {
    SipemiruTheme {
        RegisterScreen()
    }
}