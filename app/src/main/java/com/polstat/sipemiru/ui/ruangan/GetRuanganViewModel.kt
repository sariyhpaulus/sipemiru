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
import com.polstat.sipemiru.model.Ruangan
import com.polstat.sipemiru.repository.RuanganRepository
import com.polstat.sipemiru.repository.UserPreferencesRepository
import com.polstat.sipemiru.repository.UserRepository
import com.polstat.sipemiru.repository.UserState
import com.polstat.sipemiru.response.ListRuanganResponse
import com.polstat.sipemiru.response.UserResponse
import kotlinx.coroutines.launch

private const val TAG = "GetRuanganViewModel"

class GetRuanganViewModel(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val userRepository: UserRepository,
    private val ruanganRepository: RuanganRepository
): ViewModel(){
    private lateinit var listRuanganResponse: ListRuanganResponse
    private lateinit var listRuangan : List<Ruangan>
    private lateinit var userState: UserState
    private lateinit var userResponse: UserResponse

    var daftarRuanganUiState: DaftarRuanganUiState by mutableStateOf(DaftarRuanganUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            userPreferencesRepository.user.collect {
                userState = it
                try {
                    println("daftar ruangan")
                    getAllRuangan()
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

    suspend fun getAllRuangan(){
        println(userState.token)
        try{
            if(userState.isAdmin) {
                println("sebelum fetch data")
                listRuanganResponse  = ruanganRepository.getAllRuangan(userState.token)
                println("masuk admin")
                println(listRuanganResponse)
            }
            listRuangan = listRuanganResponse.data
        } catch (e: Exception){
            Log.e(TAG, "Error: ${e.message}")
            return
        }
        daftarRuanganUiState = DaftarRuanganUiState.Success(listRuangan)
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer{
                val application = (this[APPLICATION_KEY] as SipemiruApplication)
                val userRepository = application.container.userRepository
                val ruanganRepository = application.container.ruanganRepository
                GetRuanganViewModel(
                    userPreferencesRepository = application.userPreferenceRepository,
                    userRepository = userRepository,
                    ruanganRepository = ruanganRepository
                )
            }
        }
    }
}

sealed interface DaftarRuanganUiState{
    object Loading: DaftarRuanganUiState
    data class Success(val listRuangan: List<Ruangan>): DaftarRuanganUiState
    data class Error(val message: String): DaftarRuanganUiState
}