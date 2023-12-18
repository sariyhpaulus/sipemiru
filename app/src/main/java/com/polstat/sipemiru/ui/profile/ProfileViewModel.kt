package com.polstat.sipemiru.ui.profile

//class ProfileViewModel: ViewModel() {
//    private val userRepository = UserRepository()
//
//    private val _profile = MutableLiveData<ProfileResponse>()
//    val profile : MutableLiveData<ProfileResponse> = _profile
//
//    fun getProfile(token: String) {
//        println("berhasil get profile")
//        viewModelScope.launch{
//            try {
//                val response = userRepository.getProfile(token)
//                _profile.value = response
//                println(response)
//            } catch (e: Exception) {
//                println("Error ini:"+e.message)
//            }
//        }
//    }
//
//}