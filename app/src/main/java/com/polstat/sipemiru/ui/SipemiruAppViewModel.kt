package com.polstat.sipemiru.ui

import android.text.Spannable.Factory
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.polstat.sipemiru.R
import com.polstat.sipemiru.SipemiruApplication
import com.polstat.sipemiru.data.UserPreferencesRepository
import com.polstat.sipemiru.data.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SipemiruAppViewModel (
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel(){

    private val _uiState = MutableStateFlow(SipemiruAppUiState())
    val uiState : StateFlow<SipemiruAppUiState> = _uiState.asStateFlow()

    val userState: StateFlow<UserState> = userPreferencesRepository.user.map { user ->
        user
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UserState(
            "",
            "",
            "",
            "",
            isAdmin = false,
            isPeminjam = false
        )
    )

    fun showSpinner() {
        _uiState.update { currentState ->
            currentState.copy(
                showProgressDialog = true
            )
        }
    }

    fun dismissSpinner() {
        _uiState.update { currentState ->
            currentState.copy(
                showProgressDialog = false
            )
        }
    }

    fun showMessageDialog(@StringRes messageTitle: Int, @StringRes messageBody: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                showMessageDialog = true,
                messageTitle = messageTitle,
                messageBody = messageBody
            )
        }
    }

    fun dismissMessageDialog() {
        _uiState.update { currentState ->
            currentState.copy(
                showMessageDialog = false,
                messageTitle = 0,
                messageBody = 0
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferencesRepository.saveToken("")
            userPreferencesRepository.saveFirstName("")
            userPreferencesRepository.saveLastName("")
            userPreferencesRepository.saveEmail("")
            userPreferencesRepository.saveIsAdmin(false)
            userPreferencesRepository.saveIsPeminjam(false)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SipemiruApplication)
                SipemiruAppViewModel(
                    userPreferencesRepository = application.userPreferenceRepository
                )
            }
        }
    }
}

data class SipemiruAppUiState(
    val showProgressDialog: Boolean = false,
    val showMessageDialog: Boolean = false,
    @StringRes val messageTitle: Int = 0,
    @StringRes val messageBody: Int = 0
)