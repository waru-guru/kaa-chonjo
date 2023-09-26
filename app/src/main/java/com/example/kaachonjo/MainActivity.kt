package com.example.kaachonjo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.kaachonjo.navigation.AppNavHost
import com.example.kaachonjo.ui.theme.KaaChonjoTheme
import com.example.kaachonjo.ui.theme.pages.home.HomeScreen
import com.example.kaachonjo.ui.theme.pages.login.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KaaChonjoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    MainScreen()


                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainActivityScreenPreview() {
    KaaChonjoTheme {
        MainScreen()

    }
}