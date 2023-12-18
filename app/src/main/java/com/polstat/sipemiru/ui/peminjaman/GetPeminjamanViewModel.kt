package com.polstat.sipemiru.ui.peminjaman

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
import com.polstat.sipemiru.model.Peminjaman
import com.polstat.sipemiru.repository.PeminjamanRepository
import com.polstat.sipemiru.repository.UserPreferencesRepository
import com.polstat.sipemiru.repository.UserRepository
import com.polstat.sipemiru.repository.UserState
import com.polstat.sipemiru.response.ListPeminjamanResponse
import com.polstat.sipemiru.response.UserResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "GetPeminjamanViewModel"

class GetPeminjamanViewModel (
    private val userPreferencesRepository: UserPreferencesRepository,
    private val userRepository: UserRepository,
    private val peminjamanRepository: PeminjamanRepository
): ViewModel(){
    private lateinit var listPeminjamanResponse: ListPeminjamanResponse
    private lateinit var listPeminjaman : List<Peminjaman>
    private lateinit var userState: UserState
    private lateinit var userResponse: UserResponse

    var daftarPeminjamanUiState: DaftarPeminjamanUiState by mutableStateOf(DaftarPeminjamanUiState.Loading)
        private set

    init{
        viewModelScope.launch {
            userPreferencesRepository.user.collect{
                userState = it
                try{
                    println("daftar peminjaman")
                    getAllPeminjaman()
                    println("Masuk sini gak?")
                } catch (e: Exception){
                    println("Error: ${e.message}")
                    println("Error: ${e.stackTraceToString()}")
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

    suspend fun getAllPeminjaman(){
        println(userState.token)
        try{
            if(userState.isAdmin){
                println("sebelum fetch data")
                listPeminjamanResponse = peminjamanRepository.getAllPeminjaman(userState.token)
                println("masuk admin")
                println(listPeminjamanResponse)
                println("bisa fetch peminjaman")
            }
            println("masuk sini gak?")
            listPeminjaman = listPeminjamanResponse.data
        } catch (e: Exception){
            Log.e(TAG, "Error: ${e.message}")
            return
        }
        daftarPeminjamanUiState = DaftarPeminjamanUiState.Success(listPeminjaman)
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer{
                val application = (this[APPLICATION_KEY] as SipemiruApplication)
                val userRepository = application.container.userRepository
                val peminjamanRepository = application.container.peminjamanRepository
                GetPeminjamanViewModel(
                    userPreferencesRepository = application.userPreferenceRepository,
                    userRepository = userRepository,
                    peminjamanRepository = peminjamanRepository
                )
            }
        }
    }
}

sealed interface DaftarPeminjamanUiState {
    object Loading : DaftarPeminjamanUiState
    data class Success(val listPeminjaman: List<Peminjaman>) : DaftarPeminjamanUiState
    data class Error(val message: String) : DaftarPeminjamanUiState
}