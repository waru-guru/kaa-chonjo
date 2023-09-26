package com.example.kaachonjo.ui.theme.pages.home

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kaachonjo.navigation.ROUTE_CORRUPT_ACTIVITY
import com.example.kaachonjo.navigation.ROUTE_CRIMINAL_LIST
import com.example.kaachonjo.navigation.ROUTE_SUCCESS
import com.example.kaachonjo.navigation.ROUTE_SUSPICIOUS_ACTIVITY
import com.example.kaachonjo.ui.theme.Backg
import com.example.kaachonjo.ui.theme.KaaChonjoTheme
import com.example.kaachonjo.ui.theme.Others
import com.example.kaachonjo.ui.theme.Top
import com.example.kaachonjo.ui.theme.amaticFontFamily
import com.example.kaachonjo.ui.theme.caveatFontFamily
import com.example.kaachonjo.ui.theme.luckiestGuyFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController:NavHostController) {
    Box(modifier = Modifier
        .background(Backg)
        .fillMaxSize()
    ) {
        Column {
            GreetingSection()
            ReportSectionStart()
            Reports(navController = navController)


        }

    }

}

@Composable
fun GreetingSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Backg)
            .fillMaxWidth()
            .padding(20.dp)

    ) {
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "Hey there Peacemaker!",
                fontWeight = FontWeight.ExtraBold,
                fontFamily = amaticFontFamily,
                fontSize = 40.sp,
                color = Others
            )
            Text(text = "Thank you for helping keep the country safe.",
                fontSize = 30.sp,
                fontFamily = caveatFontFamily,
                color = Color.White


            )
        }

    }

}

@Composable
fun ReportSectionStart(

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(25.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Others)
            .padding(20.dp, 25.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(text = "Kindly tell us,",
                fontSize = 40.sp,
                fontFamily = amaticFontFamily,
                fontWeight = FontWeight.ExtraBold

            )
            Text(text = "What would you like to report?",
                fontSize = 30.sp,
                fontFamily = caveatFontFamily,
                fontWeight = FontWeight.ExtraBold
            )
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Reports(navController: NavHostController) {
var context = LocalContext.current
   Column(Modifier.verticalScroll(rememberScrollState())) {
      Row(
          modifier = Modifier
              .padding(top = 20.dp)
              .horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.SpaceAround
      ) {
          ElevatedCard(
              modifier = Modifier
                  .padding(10.dp)
                  .width(250.dp)
                  .height(200.dp),
              colors = CardDefaults.cardColors(Top.copy(0.5f)),
              onClick = {
                  val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+254115242320"))
                  if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                      != PackageManager.PERMISSION_GRANTED
                  ) { ActivityCompat.requestPermissions(
                          Activity(), arrayOf<String>(Manifest.permission.CALL_PHONE), 1
                      )
                      navController.navigate(ROUTE_SUCCESS)
                  } else {
                      context.startActivity(intent)
                  }
              })


           {
              Text(
                  text = "Report a Crime",
                  fontFamily = luckiestGuyFamily,
                  fontSize = 30.sp,
                  color = Others,
                  modifier = Modifier
                      .align(Alignment.CenterHorizontally)
                      .padding(top = 50.dp, start = 20.dp, end = 20.dp)
                  )

          }
          ElevatedCard(
              modifier = Modifier
                  .padding(10.dp)
                  .width(250.dp)
                  .height(200.dp),
              colors = CardDefaults.cardColors(Top.copy(0.5f)),
              onClick = { navController.navigate(ROUTE_CRIMINAL_LIST)}

          ) {
              Text(
                  text = "Report a Wanted Criminal",
                  fontFamily = luckiestGuyFamily,
                  fontSize = 30.sp,
                  color = Others,
                  modifier = Modifier
                      .align(Alignment.CenterHorizontally)
                      .padding(top = 50.dp, start = 20.dp, end = 20.dp)
                  )

          }

          ElevatedCard(
              modifier = Modifier
                  .padding(10.dp)
                  .width(250.dp)
                  .height(200.dp),
              colors = CardDefaults.cardColors(Top.copy(0.5f)),
              onClick = {navController.navigate(ROUTE_SUSPICIOUS_ACTIVITY)}

          ) {
              Text(
                  text = "Report Suspicious Activity",
                  fontFamily = luckiestGuyFamily,
                  fontSize = 30.sp,
                  color = Others,
                  modifier = Modifier
                      .align(Alignment.CenterHorizontally)
                      .padding(top = 50.dp, start = 20.dp, end = 20.dp)
                  )

          }
          ElevatedCard(
              modifier = Modifier
                  .padding(5.dp)
                  .width(250.dp)
                  .height(200.dp),
              colors = CardDefaults.cardColors(Top.copy(0.5f)),
              onClick = {navController.navigate(ROUTE_CORRUPT_ACTIVITY)}

          ) {
              Text(
                  text = "Report Corrupt Activity",
                  fontFamily = luckiestGuyFamily,
                  fontSize = 30.sp,
                  color = Others,
                  modifier = Modifier
                      .align(Alignment.CenterHorizontally)
                      .padding(top = 50.dp, start = 20.dp, end = 20.dp)
                  )

          }
      }

   }
}








@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreview() {
    KaaChonjoTheme {
        HomeScreen(rememberNavController())

    }
}

