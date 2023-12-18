package com.polstat.sipemiru.ui.profile

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
import com.polstat.sipemiru.model.EditProfileForm
import com.polstat.sipemiru.repository.UserPreferencesRepository
import com.polstat.sipemiru.repository.UserRepository
import com.polstat.sipemiru.repository.UserState
import com.polstat.sipemiru.response.UserResponse

private const val TAG = "EditProfileViewModel"

class EditProfileViewModel(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val userRepository: UserRepository,
): ViewModel(){
    private lateinit var userState: UserState
    private lateinit var userResponse: UserResponse
    private lateinit var token: String

    var firstName by mutableStateOf("")
        private set

    var lastName by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var editProfileUiState: EditProfileUiState by mutableStateOf(EditProfileUiState.Loading)
        private set

    fun onFirstNameChange(firstName: String){
        this.firstName = firstName
    }

    fun onLastNameChange(lastName: String){
        this.lastName = lastName
    }

    fun onEmailChange(email: String){
        this.email = email
    }

    companion object{
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SipemiruApplication)
                val userRepository = application.container.userRepository
                EditProfileViewModel(
                    userPreferencesRepository = application.userPreferenceRepository,
                    userRepository = userRepository
                )
            }
        }
    }

    suspend fun editProfile(): EditProfileReport{
        try{
            val editProfile = EditProfileForm(
                firstName = firstName,
                lastName = lastName,
                email = email
            )
            Log.d(TAG, "editProfile: $editProfile")
            userRepository.editProfile(editProfile)
        } catch (e: Exception){
            Log.d(TAG, "editProfile: ${e.message}")
            Log.d(TAG, "editProfile: ${e.stackTraceToString()}")
            return EditProfileReport.ERROR
        }

        return EditProfileReport.SUCCESS
    }


}

enum class EditProfileReport{
    SUCCESS,
    ERROR
}

sealed interface EditProfileUiState{
    object Loading: EditProfileUiState
    data class Success(val userResponse: UserResponse): EditProfileUiState
    data class Error(val exception: Exception): EditProfileUiState
}