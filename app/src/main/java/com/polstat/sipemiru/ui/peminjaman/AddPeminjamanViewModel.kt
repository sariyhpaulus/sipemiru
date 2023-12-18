package com.polstat.sipemiru.ui.peminjaman

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import com.polstat.sipemiru.model.AddPeminjamanForm
import com.polstat.sipemiru.repository.PeminjamanRepository
import com.polstat.sipemiru.repository.UserPreferencesRepository
import com.polstat.sipemiru.repository.UserRepository
import com.polstat.sipemiru.repository.UserState
import com.polstat.sipemiru.response.PeminjamanResponse
import com.polstat.sipemiru.response.UserResponse
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime

private const val TAG = "AddPeminjamanViewModel"

@RequiresApi(Build.VERSION_CODES.O)
class AddPeminjamanViewModel(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val userRepository: UserRepository,
    private val peminjamanRepository: PeminjamanRepository
): ViewModel() {
    private lateinit var userState: UserState
    private lateinit var userResponse: UserResponse
    private lateinit var token: String

    var ruanganId by mutableStateOf("")
        private set

    var tanggalPeminjaman by mutableStateOf("")
        private set

    var waktuMulai by mutableStateOf("")
        private set

    var waktuSelesai by mutableStateOf("")
        private set

    var keperluan by mutableStateOf("")
        private set

    var peminjamanUiState: PeminjamanUiState by mutableStateOf(PeminjamanUiState.Loading)
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

    fun onTanggalPeminjamanChange(tanggalPeminjaman: String){
        this.tanggalPeminjaman = tanggalPeminjaman
    }

    fun onWaktuMulaiChange(waktuMulai: String){
        this.waktuMulai = waktuMulai
    }

    fun onWaktuSelesaiChange(waktuSelesai: String){
        this.waktuSelesai = waktuSelesai
    }

    fun onKeperluanChange(keperluan: String){
        this.keperluan = keperluan
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SipemiruApplication)
                val userRepository = application.container.userRepository
                val peminjamanRepository = application.container.peminjamanRepository
                AddPeminjamanViewModel(
                    userPreferencesRepository = application.userPreferenceRepository,
                    userRepository = userRepository,
                    peminjamanRepository = peminjamanRepository
                )
            }
        }
    }

    suspend fun addPeminjaman(): AddPeminjamanReport {
        println(ruanganId)
        try{
            val peminjaman = AddPeminjamanForm(
                ruanganId = ruanganId,
                tanggalPeminjaman = tanggalPeminjaman,
                waktuMulai = waktuMulai,
                waktuSelesai = waktuSelesai,
                keperluan = keperluan
            )
            Log.d(TAG, peminjaman.toString())
            peminjamanRepository.addPeminjaman(token, peminjaman)
        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e.message}")
            Log.e(TAG, "Error: ${e.stackTraceToString()}")
            return AddPeminjamanReport.FAILED
        }

        return AddPeminjamanReport.SUCCESS
    }



}

enum class AddPeminjamanReport {
    SUCCESS,
    FAILED
}

sealed interface PeminjamanUiState{
    object Loading : PeminjamanUiState
    data class Success(val peminjamanResponse: PeminjamanResponse) : PeminjamanUiState
    data class Error(val exception: Throwable) : PeminjamanUiState
}