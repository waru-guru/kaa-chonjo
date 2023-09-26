package com.example.kaachonjo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kaachonjo.data.AuthRepository
import com.example.kaachonjo.data.BottomBarScreen
import com.example.kaachonjo.ui.theme.pages.home.HomeScreen
import com.example.kaachonjo.ui.theme.pages.list.AddCriminalScreen
import com.example.kaachonjo.ui.theme.pages.list.CriminalListScreen
import com.example.kaachonjo.ui.theme.pages.list.UpdateCriminalListScreen
import com.example.kaachonjo.ui.theme.pages.login.LoginScreen
import com.example.kaachonjo.ui.theme.pages.signup.SignupScreen
import com.example.kaachonjo.ui.theme.pages.success.CorruptActivityScreen
import com.example.kaachonjo.ui.theme.pages.success.SuccessScreen
import com.example.kaachonjo.ui.theme.pages.success.SuspiciousActivityScreen

@Composable
fun AppNavHost(navController:NavHostController ) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route){
        composable(route = BottomBarScreen.Home.route){
          HomeScreen(rememberNavController())
        }
        composable(route = BottomBarScreen.Profile.route){
            LoginScreen(rememberNavController())
        }
        composable(route = BottomBarScreen.Add.route){
            var context = LocalContext.current
            var authRepository = AuthRepository(navController, context)
            if (!(authRepository.isLoggedIn())){
                navController.navigate(ROUTE_LOGIN)
            }else{
                AddCriminalScreen(rememberNavController())
            }

        }
        composable(ROUTE_SIGNUP) {
            SignupScreen(navController)
        }
        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }
        composable(ROUTE_ADD_CRIMINAL) {
            AddCriminalScreen(navController)
        }
        composable(ROUTE_CRIMINAL_LIST) {
            CriminalListScreen(navController)
        }
        composable(ROUTE_SUCCESS) {
            SuccessScreen(navController)
        }
        composable(ROUTE_SUSPICIOUS_ACTIVITY) {
            SuspiciousActivityScreen(navController)
        }
        composable(ROUTE_CORRUPT_ACTIVITY) {
            CorruptActivityScreen(navController)
        }
        composable(ROUTE_UPDATE_CRIMINAL_LIST + "/{id}") { passedData ->
            UpdateCriminalListScreen(navController, passedData.arguments?.getString("id")!!)
        }
    }

}


