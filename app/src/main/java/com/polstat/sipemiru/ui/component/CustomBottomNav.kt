package com.polstat.sipemiru.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.polstat.sipemiru.ui.screen.Screen
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.polstat.sipemiru.repository.UserState
import com.polstat.sipemiru.response.UserResponse
import com.polstat.sipemiru.ui.theme.Blue40
import com.polstat.sipemiru.ui.theme.PoppinsFamily


@Composable
fun CustomBottomNavigation(
    navController: NavController,
    currentScreenId: String,
    onItemSelected: (Screen) -> Unit
){
    lateinit var userState: UserState
    lateinit var userResponse: UserResponse

    val items = Screen.ItemsAdmin.list

    Surface(
        elevation = 150.dp
    ) {
        Row(
            modifier = Modifier
                .background(color = Blue40,
                    shape = RoundedCornerShape(
                        topStart = 15.dp,
                        topEnd = 15.dp
                    )
                )
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.06f),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            items.forEach { item ->
                CustomBottomNavigationItem(item = item, isSelected = item.id == currentScreenId) {
                    navController.navigate(item.id)
                }
            }
        }
    }
}

@Composable
fun CustomBottomNavigationItem(item: Screen, isSelected: Boolean, onClick: () -> Unit) {
    val background = if (isSelected) Color.White.copy(alpha = 0.8f) else Color.Transparent
    val contentColor = if (isSelected) Blue40 else Color.White.copy(alpha = 0.6f)

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(28.dp)
            )
            AnimatedVisibility(visible = isSelected) {
                Text(
                    text = item.title,
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.Medium,
                    color = contentColor,
                    modifier = Modifier.height(28.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun Prev1() {
    val navController = rememberNavController()
    CustomBottomNavigation(navController = navController, currentScreenId = Screen.Beranda.id) {}
}


@ExperimentalAnimationApi
@Composable
@Preview(showBackground = true)
fun Prev2() {
    CustomBottomNavigationItem(item = Screen.Beranda, isSelected = true) {}
}