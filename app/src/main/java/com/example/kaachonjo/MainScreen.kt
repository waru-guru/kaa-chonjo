package com.example.kaachonjo

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kaachonjo.data.AuthRepository
import com.example.kaachonjo.data.BottomBarScreen
import com.example.kaachonjo.navigation.AppNavHost
import com.example.kaachonjo.navigation.ROUTE_HOME
import com.example.kaachonjo.navigation.ROUTE_LOGIN
import com.example.kaachonjo.ui.theme.Backg
import com.example.kaachonjo.ui.theme.Others
import com.example.kaachonjo.ui.theme.Top
import com.example.kaachonjo.ui.theme.luckiestGuyFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController)},
        topBar = { TopAppBar(modifier = Modifier, navController = navController)}
    ) {
        paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            AppNavHost(navController = navController)
        }

    }

}

@Composable
fun BottomBar(navController:NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.List,
        BottomBarScreen.Add
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    NavigationBar(containerColor = Backg, contentColor = Others) {
        screens.forEach  { screen->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val context = LocalContext.current
    var authRepository = AuthRepository(navController, context)

    NavigationBarItem(
        label = { Text(text = screen.title)},
        icon = { Icon(imageVector = screen.icon, contentDescription = null)} ,
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route } == true,
        onClick = {
            if (screen.route.equals(ROUTE_HOME)){
                navController.navigate(screen.route){
                    popUpTo(navController.graph.findStartDestination().id){
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }else{
                if (!authRepository.isLoggedIn()){
                    navController.navigate(ROUTE_LOGIN)
                }else{
                    navController.navigate(screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        },
        colors = NavigationBarItemDefaults.colors(
            unselectedIconColor = Color.White, selectedIconColor = Others
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier, navController: NavHostController){
    var context = LocalContext.current
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Top

        ),
        navigationIcon = { IconButton(onClick = {

            var authRepository = AuthRepository(navController, context)
            if (authRepository.isLoggedIn()){
                authRepository.logout()
            }else{
                navController.navigate(ROUTE_LOGIN)
            }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.logout),
                contentDescription = "logout icon")

        }},
        title = { Text(
            text = AnnotatedString("KAA CHONJO!"),
            fontFamily = luckiestGuyFamily,
            fontWeight = FontWeight.ExtraBold,
            color = Others,
            fontSize = 40.sp,
        )},


    )
}