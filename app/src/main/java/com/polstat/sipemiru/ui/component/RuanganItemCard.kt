package com.polstat.sipemiru.ui.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polstat.sipemiru.ui.theme.Blue40
import com.polstat.sipemiru.ui.theme.Blue60
import com.polstat.sipemiru.ui.theme.Blue80
import com.polstat.sipemiru.ui.theme.PoppinsFamily
import com.polstat.sipemiru.ui.theme.Typography

@Composable
fun RuanganItemCard(
    ruanganId: String,
    namaRuangan: String,
    gedung: String,
    lantai: String,
    kapasitas: String,
    modifier: Modifier = Modifier,
    options: @Composable (() -> Unit) = {}
) {
    //var expanded by rememberSaveable{ mutableStateOf(false) }
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
                   imageVector = Icons.Filled.MeetingRoom,
                   contentDescription = "Ruangan",
                   tint = Blue80,
                   modifier = Modifier.size(40.dp)
               )
                Text(
                    text = namaRuangan,
                    style = Typography.bodyMedium,
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = "Gedung $gedung, Lantai $lantai",
                style = Typography.bodySmall,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Kapasitas: $kapasitas",
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
fun ListRuanganItemCard(
    ruanganId: String = "R001",
    namaRuangan: String = "Ruang 1",
    gedung: String = "Gedung A",
    lantai: String = "Lantai 1",
    kapasitas: String = "100",
    modifier: Modifier.Companion = Modifier,
    options: () -> Unit = {}
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth().fillMaxSize()
            .padding(16.dp)
            .background(color = Blue40),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
    ) {
        this.items(items = listOf(1,2,3,4,5,6,7,8,9,10), key = { ruangan -> ruangan}){
            RuanganItemCard(
                ruanganId = ruanganId,
                namaRuangan = namaRuangan,
                gedung = gedung,
                lantai = lantai,
                kapasitas = kapasitas
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RuanganItemCardPreview() {
    RuanganItemCard(
        ruanganId = "R001",
        namaRuangan = "Ruang 1",
        gedung = "Gedung A",
        lantai = "Lantai 1",
        kapasitas = "100",
        modifier = Modifier,
        options = {}
    )
}