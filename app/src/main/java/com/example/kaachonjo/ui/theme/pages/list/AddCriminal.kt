package com.example.kaachonjo.ui.theme.pages.list

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.dynamicLightColorScheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kaachonjo.data.AuthRepository
import com.example.kaachonjo.data.CriminalRepository
import com.example.kaachonjo.navigation.ROUTE_LOGIN
import com.example.kaachonjo.ui.theme.Backg
import com.example.kaachonjo.ui.theme.KaaChonjoTheme
import com.example.kaachonjo.ui.theme.Others
import com.example.kaachonjo.ui.theme.luckiestGuyFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCriminalScreen(navController:NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Backg)
            .padding(50.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding()
                .verticalScroll(rememberScrollState())
                .background(Backg),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp, alignment = Alignment.CenterVertically) ){
            var context = LocalContext.current


            Text(
                text = "Upload Wanted Criminals",
                color = Others,
                fontSize = 30.sp,
                fontFamily = luckiestGuyFamily,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 30.dp)

            )
            var criminalName by remember { mutableStateOf(TextFieldValue("")) }
            var criminalDescription by remember { mutableStateOf(TextFieldValue("")) }
            var imageUrl by remember { mutableStateOf(TextFieldValue("")) }




            TextField(
                value = criminalName,
                onValueChange = { criminalName = it},
                label = { Text(text = "Enter criminal name *") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )


            TextField(
                value = criminalDescription,
                onValueChange = { criminalDescription = it},
                label = { Text(text = "Enter Description *") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )



            ImagePicker(modifier = Modifier,context, navController,criminalName.text.trim(),criminalDescription.text.trim() )

        }
    }
}

@Composable
fun ImagePicker(modifier: Modifier = Modifier, context: Context, navController: NavHostController, name:String, description:String) {
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
                text = "Upload Image *",
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
                criminalRepository.saveCriminalWithImage(name,description,imageUri!!)
            },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Others)) {
                Text(text = "Upload")
            }
        }
    }
}




@Preview
@Composable
fun AddCriminalScreenPreview() {
    KaaChonjoTheme {
        AddCriminalScreen(rememberNavController())
    }

}