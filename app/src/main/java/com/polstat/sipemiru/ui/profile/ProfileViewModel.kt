package com.polstat.sipemiru.ui.profile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.polstat.sipemiru.SipemiruApplication
import com.polstat.sipemiru.repository.UserPreferencesRepository
import com.polstat.sipemiru.repository.UserRepository
import com.polstat.sipemiru.repository.UserState
import com.polstat.sipemiru.response.UserResponse
import kotlinx.coroutines.launch

private const val TAG = "ProfileViewModel"

class ProfileViewModel(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private lateinit var userState: UserState
    private lateinit var userResponse: UserResponse

    var profileUiState: ProfileUiState by mutableStateOf(ProfileUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            userPreferencesRepository.user.collect {
                userState = it
                try {
                    println("profile")
                    getProfile()
                    println("Masuk sini gak?")
                } catch (e: Exception) {
                    Log.e(TAG, "Error: ${e.message}")
                    Log.e(TAG, "Error: ${e.stackTraceToString()}")
                }
            }
        }

    }
        fun isAdmin(): Boolean {
            return userState.isAdmin
        }

        fun isPeminjam(): Boolean {
            return userState.isPeminjam
        }

        suspend fun getProfile() {
            println(userState.token)
            try {
                userResponse = userRepository.showProfile(userState.token)

            } catch (e: Exception) {
                Log.e(TAG, "Error: ${e.message}")
                return
            }
            profileUiState = ProfileUiState.Success(userResponse)
        }

        companion object {
            val Factory: ViewModelProvider.Factory = viewModelFactory {
                initializer {
                    val application = (this[APPLICATION_KEY] as SipemiruApplication)
                    val userRepository = application.container.userRepository
                    ProfileViewModel(
                        userPreferencesRepository = application.userPreferenceRepository,
                        userRepository = userRepository
                    )
                }
            }

        }

}

sealed interface ProfileUiState{
    object Loading : ProfileUiState
    data class Success(val userResponse: UserResponse) : ProfileUiState
    data class Error(val exception: Exception) : ProfileUiState
}