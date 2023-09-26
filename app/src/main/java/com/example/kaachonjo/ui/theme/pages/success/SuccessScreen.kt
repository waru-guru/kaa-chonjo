package com.example.kaachonjo.ui.theme.pages.success

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kaachonjo.R
import com.example.kaachonjo.navigation.ROUTE_HOME
import com.example.kaachonjo.ui.theme.Backg
import com.example.kaachonjo.ui.theme.KaaChonjoTheme
import com.example.kaachonjo.ui.theme.Others
import com.example.kaachonjo.ui.theme.Top
import com.example.kaachonjo.ui.theme.amaticFontFamily
import com.example.kaachonjo.ui.theme.caveatFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessScreen(navController:NavHostController) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            colors = CardDefaults.cardColors(Top),
            modifier = Modifier
                .width(340.dp)
                .height(300.dp)


        ) {
            Image(
                painter = painterResource(id = R.drawable.checked),
                contentDescription = null,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 10.dp, bottom = 15.dp)
            )
            Text(
                text = "Report sent successfully!",
                fontSize = 35.sp,
                color = Others,
                fontFamily = amaticFontFamily,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(bottom = 15.dp)
            )

            Text(
                text = "Thank you for contributing to the peace and prosperity of the nation!",
                fontSize = 28.sp,
                color = Color.White,
                fontFamily = caveatFontFamily,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)



            )
        }


    }

}




@Preview
@Composable
fun SuccessScreenPreview() {
    KaaChonjoTheme {
        SuccessScreen(rememberNavController())
    }

}