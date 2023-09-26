package com.example.kaachonjo.ui.theme.pages.login

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
//import androidx.compose.ui.text.style.TextForegroundStyle.Unspecified.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kaachonjo.R
import com.example.kaachonjo.data.AuthRepository
import com.example.kaachonjo.navigation.ROUTE_HOME
import com.example.kaachonjo.navigation.ROUTE_LOGIN
import com.example.kaachonjo.navigation.ROUTE_SIGNUP
import com.example.kaachonjo.navigation.ROUTE_UPDATE_CRIMINAL_LIST
import com.example.kaachonjo.ui.theme.Backg
import com.example.kaachonjo.ui.theme.KaaChonjoTheme
import com.example.kaachonjo.ui.theme.Others
import com.example.kaachonjo.ui.theme.amaticFontFamily
import com.example.kaachonjo.ui.theme.luckiestGuyFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController:NavHostController) {
    var context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Backg)
            .padding(50.dp)
    ) {
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Backg),
            verticalArrangement = Arrangement.spacedBy(18.dp, alignment = Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hey there,",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 30.sp,
                    fontFamily = amaticFontFamily,
                    fontWeight = FontWeight.ExtraBold),
                color = Color.White,)
            Text(
                color = Others,
                text ="Welcome Back!",
                fontFamily = luckiestGuyFamily,
                fontSize = 35.sp,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 40.sp),

            )

            Image(
                painter = painterResource(id = R.drawable.wave),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .align(CenterHorizontally)
                    .padding(vertical = 5.dp)
            )


            var email by remember { mutableStateOf(TextFieldValue("")) }
            var password by remember { mutableStateOf(TextFieldValue("")) }

            TextField(
                value = email,
                onValueChange = {email = it},
                label = { Text(text = "Email")},
                leadingIcon = { androidx.compose.material3.Icon(
                    imageVector = Icons.Default.MailOutline,
                    contentDescription = null
                )}
            )

            TextField(
                value = password,
                onValueChange = {password = it},
                label = { Text(text = "Password")},
                leadingIcon = { androidx.compose.material3.Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )}
            )
            
            Button(
                onClick = {
                    var authRepository = AuthRepository(navController, context)
                    if (!authRepository.isLoggedIn()){
                        authRepository.login(email.text.trim(),password.text.trim())
                    }else{
                        navController.navigate(ROUTE_HOME)
                    }

                },
                colors = ButtonDefaults.buttonColors(Others),
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = "Login",
                    fontWeight = FontWeight.Bold
                )
            }
            Divider(modifier = Modifier
                .height(2.dp)
                .alpha(0.3f)
            )


            ClickableText(
                text = AnnotatedString("Don't have account? Signup"),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                ),
                onClick = {navController.navigate(ROUTE_SIGNUP)}
            )
        }

    }
    
}
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun LoginScreenPreview() {
    KaaChonjoTheme {
        LoginScreen(rememberNavController())
    }

}