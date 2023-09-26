package com.example.kaachonjo.ui.theme.pages.signup

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kaachonjo.R
import com.example.kaachonjo.data.AuthRepository
import com.example.kaachonjo.navigation.ROUTE_LOGIN
import com.example.kaachonjo.ui.theme.Backg
import com.example.kaachonjo.ui.theme.KaaChonjoTheme
import com.example.kaachonjo.ui.theme.Others
import com.example.kaachonjo.ui.theme.luckiestGuyFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController:NavHostController) {
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
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            var context = LocalContext.current


//            Image(
//                painter = painterResource(id = R.drawable.camera),
//                contentDescription = null,
//                modifier = Modifier
//                    .height(80.dp)
//                    .width(50.dp)
//                    .align(CenterHorizontally)
//                    .padding(vertical = 5.dp)
//
//                )

            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "Hey there,",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive
                    )

            )
            Text(
                text = "Signup here",
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = luckiestGuyFamily,
                fontSize = 30.sp,
                color = Others,
                fontWeight = FontWeight.ExtraBold,
            )

            var email by remember { mutableStateOf(TextFieldValue("")) }
            var password by remember { mutableStateOf(TextFieldValue("")) }
            var name by remember { mutableStateOf(TextFieldValue("")) }

            TextField(
                value = name,
                onValueChange = { name = it},
                leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null)},
                label = { Text(text = "Name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            TextField(
                value = email,
                onValueChange = { email = it},
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null)},
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            TextField(
                value = password,
                onValueChange = { password = it},
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null)},
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Button(
                onClick = {
                var authRepository = AuthRepository(navController,context)
                authRepository.signup(name.text.trim(), email.text.trim(), password.text.trim()) },
                colors = ButtonDefaults.buttonColors(Others),
                modifier = Modifier.fillMaxWidth()) {

                Text(
                    text = "Sign up")
            }

            Text(
                text = "Already have an account?",
                color = Others,

            )

            Divider(modifier = Modifier
                .height(2.dp)
                .alpha(0.3f))


            ClickableText(
                text = AnnotatedString("Login instead"),
                onClick = {navController.navigate(ROUTE_LOGIN)},
                style = MaterialTheme.typography.bodyMedium.copy(
                    textDecoration = TextDecoration.Underline,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun SignupScreenPreview() {
    KaaChonjoTheme {
        SignupScreen(rememberNavController())


    }
}