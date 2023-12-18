package com.polstat.sipemiru.ui.auth

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.polstat.sipemiru.SipemiruApplication
import com.polstat.sipemiru.model.RegisterForm
import com.polstat.sipemiru.repository.UserRepository


private const val TAG = "RegisterViewModel"

enum class RegisterResult{
    Success,
    EmptyField,
    PasswordNotMatch,
    EmailNotValid,
    NetworkError
}

class RegisterViewModel (
    private val userRepository: UserRepository
): ViewModel(){
    var firstNameField by mutableStateOf("")
        private set

    var lastNameField by mutableStateOf("")
        private set

    var emailField by mutableStateOf("")
        private set

    var passwordField by mutableStateOf("")
        private set

    var confirmPasswordField by mutableStateOf("")
        private set

    fun updateFirstNameField(firstName: String){
        firstNameField = firstName
    }

    fun updateLastNameField(lastName: String){
        lastNameField = lastName
    }

    fun updateEmailField(email: String){
        emailField = email
    }

    fun updatePasswordField(password: String){
        passwordField = password
    }

    fun updateConfirmPasswordField(confirmPassword: String){
        confirmPasswordField = confirmPassword
    }

    suspend fun register(): RegisterResult{
        if(firstNameField == "" || lastNameField == "" || emailField == "" || passwordField == "" || confirmPasswordField == ""){
            return RegisterResult.EmptyField
        }
        if(passwordField != confirmPasswordField){
            return RegisterResult.PasswordNotMatch
        }

        try{
            val register = RegisterForm(
                role_id = 2,
                firstName = firstNameField,
                lastName = lastNameField,
                email = emailField,
                password = passwordField
            )
            Log.d(TAG, register.toString())
            userRepository.register(register)
        } catch (e: Exception){
            Log.e(TAG, "Error: ${e.message}")
            return RegisterResult.NetworkError
        }
        return RegisterResult.Success
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer{
                val application = (this[APPLICATION_KEY] as SipemiruApplication)
                val userRepository = application.container.userRepository
                RegisterViewModel(
                    userRepository = userRepository
                )
            }
        }
    }

}