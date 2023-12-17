package com.polstat.sipemiru.ui.ruangan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polstat.sipemiru.data.RuanganRepository
import com.polstat.sipemiru.request.RuanganRequest
import com.polstat.sipemiru.response.RuanganResponse
import com.polstat.sipemiru.service.SessionManager
import kotlinx.coroutines.launch

class RuanganViewModel() : ViewModel(){
    private val ruanganRepository = RuanganRepository()

    private val _ruanganResponse = MutableLiveData<RuanganResponse>()
    //private val _loginResponse = MutableLiveData<RuanganResponse>()
    val ruanganResponse : MutableLiveData<RuanganResponse> = _ruanganResponse

    fun requestRuangan(
        ruanganId: String,
        namaRuangan: String,
        gedung: String,
        lantai: String,
        kapasitas: String
    ){
        println("berhasil")
        println(ruanganId)

        //val token = SessionManager(this).fetchAuthToken()

        viewModelScope.launch {
            try {
                println("berhasil 2")
                println(namaRuangan)
                val response = ruanganRepository.requestForRuangan(RuanganRequest(ruanganId, namaRuangan, gedung, lantai, kapasitas))
                _ruanganResponse.value = response
                println(response)
            } catch (e: Exception) {
                println("Error ini:"+e.message)

            }
        }
    }
}