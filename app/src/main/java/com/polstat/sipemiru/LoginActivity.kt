package com.polstat.sipemiru

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.polstat.sipemiru.navigation.AppNavHost
import com.polstat.sipemiru.request.LoginRequest
import com.polstat.sipemiru.response.LoginResponse
import com.polstat.sipemiru.service.ApiClient
import com.polstat.sipemiru.service.SessionManager
import com.polstat.sipemiru.ui.theme.SipemiruTheme
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            window.statusBarColor = getColor(R.color.black)
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

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        apiClient.getApiService(this).login(LoginRequest(email = "s@sample.com", password = "mypassword"))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Error logging in
                    System.out.println("Error: Login failed. ${t.message}")
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val loginResponse = response.body()

                    if (loginResponse?.status == 200 && loginResponse.data != null) {
                        sessionManager.saveAuthToken(loginResponse.data.accessToken)
                    } else {
                        // Error logging in
                        println("Error: Login failed. ${loginResponse?.message}")
                    }
                }
            })

    }
}