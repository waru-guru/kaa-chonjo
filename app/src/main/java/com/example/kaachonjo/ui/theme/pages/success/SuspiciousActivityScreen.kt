package com.example.kaachonjo.ui.theme.pages.success

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kaachonjo.R
import com.example.kaachonjo.navigation.ROUTE_SUCCESS
import com.example.kaachonjo.ui.theme.Backg
import com.example.kaachonjo.ui.theme.KaaChonjoTheme
import com.example.kaachonjo.ui.theme.Others
import com.example.kaachonjo.ui.theme.Top
import com.example.kaachonjo.ui.theme.amaticFontFamily
import com.example.kaachonjo.ui.theme.caveatFontFamily
import com.example.kaachonjo.ui.theme.luckiestGuyFamily

@Composable
fun SuspiciousActivityScreen(navController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Backg)
    ) {
        Column {
            GreetingSection()
            ContentSection()
            DescriptionSection(rememberNavController())
            
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
            Text(text = "Notice anything.... WEIRD?!",
                fontWeight = FontWeight.ExtraBold,
                fontFamily = amaticFontFamily,
                fontSize = 40.sp,
                color = Others
            )

        }
    }
}

@Composable
fun ContentSection(color: Color = Top) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(25.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .fillMaxWidth()
            .padding(20.dp, 20.dp)

    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.eyes),
                contentDescription = null,
                modifier = Modifier.align(CenterHorizontally))


            Text(text = "Hey... Relaxxx, it ain't snitching, it's...",
                fontSize = 40.sp,
                fontFamily = caveatFontFamily,
                color = Color.Black

            )
            Text(
                text = "PROMOTING PEACE!!",
                fontSize = 33.sp,
                fontFamily = luckiestGuyFamily,
                color = Color.White,
                textAlign = TextAlign.Center

            )
        }
    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionSection(navController:NavHostController) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(20.dp)

        ) {
            var userDescription by remember { mutableStateOf(TextFieldValue("")) }
            val maxLength = 2000
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp, alignment = Alignment.CenterVertically)){
                TextField(
                    value = userDescription,
                    onValueChange = { if (it.text.length <= maxLength){
                        userDescription = it } },
                    label = { Text(
                        text = "Kindly describe the activity *",
                        color = Color.Black )},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                Button(
                    onClick = {

                        navController.navigate(ROUTE_SUCCESS)

                    },
                    colors = ButtonDefaults.buttonColors(Others),
                    modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Report",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                }

            }

        }
    }
    


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SuspiciousActivityScreenPreview() {
    KaaChonjoTheme {
        SuspiciousActivityScreen(rememberNavController())
    }

}