package com.polstat.sipemiru.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.polstat.sipemiru.ui.theme.Base
import com.polstat.sipemiru.ui.theme.Blue20
import com.polstat.sipemiru.ui.theme.Blue80
import com.polstat.sipemiru.ui.theme.SipemiruTheme

@Preview
@Composable
fun LogOutScreenPreview() {
    SipemiruTheme {
        LogOutScreen(navController = rememberNavController())
    }
}

@Composable
fun LogOutScreen(
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(300.dp)
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(
                                color = Blue80,
                                shape = RoundedCornerShape(30.dp)
                            )
                            .padding(horizontal = 40.dp)
                    ){
                        Text(
                            text = "StisFess!",
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Medium,
                            color = Base,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    ElevatedCard(
                        modifier = Modifier
                            .height(400.dp)
                            .width(300.dp),
                        elevation = CardDefaults.elevatedCardElevation(10.dp),
                        colors = CardDefaults.cardColors(Blue20),
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top,
                        ) {
                            Spacer(modifier = Modifier.height(5.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = Base,
                                        shape = RoundedCornerShape(20.dp)
                                    )
                            ){
                                Column(
                                    modifier = Modifier.padding(15.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                ) {

                                    Spacer(modifier = Modifier.height(10.dp))
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Button(
                                        modifier = Modifier.width(100.dp),
                                        onClick = {},
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Blue80,
                                            contentColor = Base
                                        )
                                    ){
                                        Text("Login")
                                    }
                                    Spacer(modifier = Modifier.height(30.dp))
                                    Text(
                                        text = "Belum punya akun? Daftar disini!"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


