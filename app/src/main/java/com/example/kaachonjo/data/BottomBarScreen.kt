package com.example.kaachonjo.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.kaachonjo.navigation.ROUTE_ADD_CRIMINAL
import com.example.kaachonjo.navigation.ROUTE_CRIMINAL_LIST

sealed class BottomBarScreen(
    val route:String,
    val title:String,
    val icon:ImageVector,
){
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Profile: BottomBarScreen(
        route = "login",
        title = "Profile",
        icon = Icons.Default.Person
    )
    object List: BottomBarScreen(
        route = ROUTE_CRIMINAL_LIST,
        title = "List",
        icon = Icons.Default.List
    )
    object Add: BottomBarScreen(
        route = ROUTE_ADD_CRIMINAL,
        title = "Add",
        icon = Icons.Default.Add
    )
}