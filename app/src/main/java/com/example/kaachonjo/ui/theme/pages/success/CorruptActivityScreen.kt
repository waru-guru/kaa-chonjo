package com.example.kaachonjo.ui.theme.pages.success

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
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
import com.example.kaachonjo.data.CriminalRepository
import com.example.kaachonjo.navigation.ROUTE_SUCCESS
import com.example.kaachonjo.ui.theme.Backg
import com.example.kaachonjo.ui.theme.KaaChonjoTheme
import com.example.kaachonjo.ui.theme.Others
import com.example.kaachonjo.ui.theme.luckiestGuyFamily
import com.example.kaachonjo.ui.theme.pages.list.ImagePicker

@Composable
fun CorruptActivityScreen(navController:NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Backg)
    ) {
        Column {
            IntroSection()
            CorruptionSection(navController = navController)

        }
    }
}

@Composable
fun IntroSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Backg)
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Say NO to Corruption!",
                fontFamily = luckiestGuyFamily,
                fontSize = 33.sp,
                textAlign = TextAlign.Center,
                color = Others,
                modifier = Modifier.padding(bottom = 25.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.corruption),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CorruptionSection(navController:NavHostController) {
    var context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(20.dp)

    ) {
        var corruptActivityDesc by remember { mutableStateOf(TextFieldValue("")) }
        var imageUrl by remember { mutableStateOf(TextFieldValue("")) }
        val maxChar = 500
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp, alignment = Alignment.Bottom)) {
            TextField(
                value = corruptActivityDesc,
                onValueChange =  {if (it.text.length <= maxChar){
                    corruptActivityDesc = it }},
                label = { Text(text = "Kindly Describe the activity * ")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            com.example.kaachonjo.ui.theme.pages.success.ImagePicker(
                modifier = Modifier,
                context = context,
                navController = navController,
                description = corruptActivityDesc.text.trim()
            )


        }
    }
    
}

@Composable
fun ImagePicker(modifier: Modifier, context: Context, navController: NavHostController, description:String) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = modifier,) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,imageUri)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Upload Image of corrupt activity *",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { imagePicker.launch("image/*") }
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White)
                    .padding(vertical = 15.dp, horizontal = 15.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    var criminalRepository = CriminalRepository(navController,context)
                    criminalRepository.saveUserInputWithImage(description,imageUri!!)
                    navController.navigate(ROUTE_SUCCESS)

                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Others)) {
                Text(text = "Upload")
            }
        }
    }
}
    



@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CorruptActivityScreenPreview() {
    KaaChonjoTheme {
        CorruptActivityScreen(rememberNavController())
    }
    
}