package com.polstat.sipemiru

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.polstat.sipemiru.navigation.AppNavHost
import com.polstat.sipemiru.service.RetrofitInstance
import com.polstat.sipemiru.service.SessionManager
import com.polstat.sipemiru.ui.theme.SipemiruTheme

class MainActivity : ComponentActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: RetrofitInstance

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            window.statusBarColor = getColor(R.color.black)
            SipemiruTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost()
                }
            }
        }
    }

//    override fun onStart() {
//        super.onStart()
//        apiClient = RetrofitInstance
//        sessionManager = SessionManager(this)
//
//        lifecycleScope.launch {
//            try {
//                val response = apiClient.loginApiService.login(
//                    LoginRequest(email = "s@sample.com", password = "mypassword")
//                )
//
//                fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                    val loginResponse = response.body()
//
//                    if (loginResponse?.status == 200 && loginResponse.data != null) {
//                        sessionManager.saveAuthToken(loginResponse.data.accessToken)
//                    } else {
//                        // Error logging in
//                        println("Error: Login failed. ${loginResponse?.message}")
//                    }
//                }
//            } catch (e: Exception) {
//                println("Error ini:"+e.message)
//            }
//        }
//    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SipemiruTheme {
        Greeting("Android")
    }
}