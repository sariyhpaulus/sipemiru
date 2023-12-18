package com.polstat.sipemiru.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.polstat.sipemiru.ui.theme.Blue40
import com.polstat.sipemiru.ui.theme.Blue80
import com.polstat.sipemiru.ui.theme.PoppinsFamily
import com.polstat.sipemiru.ui.theme.Typography

@Composable
fun PeminjamanItemCard(
    //ruanganId: String,
    namaRuangan: String,
    tanggalPeminjaman: String,
    waktuMulai: String,
    waktuSelesai: String,
    status: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(bottom = 16.dp),
        elevation =  CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ){
                Icon(
                    imageVector = Icons.Filled.Bookmark,
                    contentDescription = "Ruangan",
                    tint = Blue80,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = namaRuangan,
                    fontSize = 18.sp,
                    //style = Typography.bodySmall,
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.SemiBold
                )
                val backgroundColor = when (status) {
                    "DITERIMA" -> Blue80
                    "MENUNGGU_KONFIRMASI" -> Color.DarkGray // Adjust the color for "Occupied" status
                    "DITOLAK" -> Color.Red
                    else -> Color.Gray // Default color for unknown status
                }
                val status = when (status) {
                    "DITERIMA" -> "Diterima"
                    "MENUNGGU_KONFIRMASI" -> "Diajukan"
                    "DITOLAK" -> "Ditolak"
                    else -> "Unknown" // Default status for unknown status
                }
                Spacer(modifier = Modifier.size(40.dp))
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(color = backgroundColor, shape = MaterialTheme.shapes.small)
                        .padding(start = 6.dp, end = 6.dp, top = 4.dp, bottom = 4.dp)
                ) {
                    Text(
                        text = status,
                        style = Typography.bodySmall,
                        fontFamily = PoppinsFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }
            Text(
                text = "Tanggal Peminjaman: $tanggalPeminjaman",
                style = Typography.bodySmall,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Waktu: $waktuMulai - $waktuSelesai (WIB)",
                style = Typography.bodySmall,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun ListPeminjamanItemCard(
    //ruanganId: String = "R001",
    namaRuangan: String = "Ruang 1",
    tanggalPeminjaman: String = "2021-10-10",
    waktuMulai: String = "08:00",
    waktuSelesai: String = "10:00",
    status: String = "DITERIMA",
){
    LazyColumn(
        modifier = Modifier
        .fillMaxWidth().fillMaxSize()
        .padding(16.dp)
        .background(color = Blue40),
        contentPadding = PaddingValues(16.dp)
    ){
        this.items(items = listOf(1,2,3,4,5,6,7,8,9,10), key = { peminjaman -> peminjaman}){
            PeminjamanItemCard(
                //ruanganId = ruanganId,
                namaRuangan = namaRuangan,
                tanggalPeminjaman = tanggalPeminjaman,
                waktuMulai = waktuMulai,
                waktuSelesai = waktuSelesai,
                status = status
            )
        }
    }
}
