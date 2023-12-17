package com.polstat.sipemiru.request

data class RuanganRequest(
    val ruanganId: String,
    val namaRuangan: String,
    val gedung: String,
    val lantai: String,
    val kapasitas: String
)
//    "ruanganId":"G3R345",
//    "namaRuangan":"Ruang Kelas 345",
//    "gedung":"3",
//    "lantai":"4",
//    "kapasitas":"40 orang"
