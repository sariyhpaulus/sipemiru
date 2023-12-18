package com.polstat.sipemiru.ui.ruangan

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
import com.polstat.sipemiru.repository.RuanganRepository
import com.polstat.sipemiru.repository.UserPreferencesRepository
import com.polstat.sipemiru.repository.UserRepository
import com.polstat.sipemiru.repository.UserState
import com.polstat.sipemiru.model.AddRuanganForm
import com.polstat.sipemiru.response.UserResponse
import kotlinx.coroutines.launch


private const val TAG = "RuanganViewModel"

class RuanganViewModel(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val userRepository: UserRepository,
    private val ruanganRepository: RuanganRepository
): ViewModel(){
    private lateinit var userState: UserState
    private lateinit var userResponse: UserResponse
    private lateinit var token: String

    var ruanganId by mutableStateOf("")
        private set
    var namaRuangan by mutableStateOf("")
        private set
    var gedung by mutableStateOf("")
        private set
    var lantai by mutableStateOf("")
        private set
    var kapasitas by mutableStateOf("")
        private set

    var ruanganUiState: RuanganUiState by mutableStateOf(RuanganUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            userPreferencesRepository.user.collect{user ->
                token = user.token
            }
        }
    }

    fun onRuanganIdChange(ruanganId: String){
        this.ruanganId = ruanganId
    }
    fun onNamaRuanganChange(namaRuangan: String){
        this.namaRuangan = namaRuangan
    }
    fun onGedungChange(gedung: String): String{
        this.gedung = gedung
        return gedung
    }
    fun onLantaiChange(lantai: String): String{
        this.lantai = lantai
        return lantai
    }
    fun onKapasitasChange(kapasitas: String){
        this.kapasitas = kapasitas
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SipemiruApplication)
                val userRepository = application.container.userRepository
                val ruanganRepository = application.container.ruanganRepository
                RuanganViewModel(
                    userPreferencesRepository = application.userPreferenceRepository,
                    userRepository = userRepository,
                    ruanganRepository = ruanganRepository
                )
            }
        }
    }

    suspend fun addRuangan(): AddRuanganReport {
        println(ruanganId)
        try{
            val ruangan = AddRuanganForm(
                ruanganId = ruanganId,
                namaRuangan = namaRuangan,
                gedung = gedung,
                lantai = lantai,
                kapasitas = kapasitas
            )
            Log.d(TAG, ruangan.toString())
            ruanganRepository.addRuangan(token, ruangan)
        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e.message}")
            Log.e(TAG, "Error: ${e.stackTraceToString()}")
            return AddRuanganReport.Error
        }

        return AddRuanganReport.Success
    }
}


enum class AddRuanganReport {
    Success,
    BadInput,
    Error
}

sealed interface RuanganUiState{
    object Loading : RuanganUiState
    data class Success(val data: UserResponse) : RuanganUiState
    data class Error(val exception: Throwable) : RuanganUiState
}