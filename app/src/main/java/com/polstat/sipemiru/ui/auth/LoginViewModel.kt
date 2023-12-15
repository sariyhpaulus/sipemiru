package com.polstat.sipemiru.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polstat.sipemiru.repository.LoginRepository
import com.polstat.sipemiru.request.LoginRequest
import com.polstat.sipemiru.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginRepository()

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse : LiveData<LoginResponse> = _loginResponse

    fun requestLogin(email: String, password: String) {
        println("Masuk sini")
        viewModelScope.launch {
            try {
                println("Masuk sini 2")
                val response = loginRepository.requestForLogin(LoginRequest(email, password))
                _loginResponse.value = response
                println(response)
            } catch (e: Exception) {
                println("Error ini:"+e.message)
            }
        }
    }
}