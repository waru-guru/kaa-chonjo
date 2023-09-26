package com.example.kaachonjo.ui.theme.pages.list

import android.content.Context
import android.content.res.Configuration
import android.media.Image
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
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
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kaachonjo.data.CriminalRepository
import com.example.kaachonjo.models.Criminal
import com.example.kaachonjo.ui.theme.Backg
import com.example.kaachonjo.ui.theme.KaaChonjoTheme
import com.example.kaachonjo.ui.theme.Others
import com.example.kaachonjo.ui.theme.luckiestGuyFamily
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateCriminalListScreen(navController:NavHostController, id:String) {
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
           verticalArrangement = Arrangement.spacedBy(18.dp, alignment = Alignment.CenterVertically)) {

           var context = LocalContext.current
           var name by remember { mutableStateOf("") }
           var description by remember { mutableStateOf("") }
           var imageUrl by remember { mutableStateOf("") }

           var currentDataRef = FirebaseDatabase.getInstance().getReference()
               .child("Criminals/$id")
           currentDataRef.addValueEventListener(object: ValueEventListener {
               override fun onDataChange(snapshot: DataSnapshot) {
                   var criminal = snapshot.getValue(Criminal::class.java)
                   name = criminal!!.name
                   description = criminal!!.description
                   imageUrl = criminal!!.imageUrl
               }

               override fun onCancelled(error: DatabaseError) {
                   Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
               }
           })

           Text(
               text = "Update Criminal Entry",
               fontSize = 30.sp,
               fontFamily = luckiestGuyFamily,
               color = Others,
               textAlign = TextAlign.Center,
               modifier = Modifier
                   .padding(bottom = 30.dp)
           )

           var criminalName by remember { mutableStateOf(TextFieldValue("")) }
           var criminalDescription by remember { mutableStateOf(TextFieldValue("")) }
           var criminalPic by remember { mutableStateOf(TextFieldValue("")) }


           TextField(
               value = criminalName,
               onValueChange = { criminalName = it },
               label = { Text(text = "Enter criminal name *") },
               keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
               modifier = Modifier.fillMaxWidth()
           )


           TextField(
               value = criminalDescription,
               onValueChange = { criminalDescription = it },
               label = { Text(text = "Enter criminal description *") },
               keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
               modifier = Modifier.fillMaxWidth()
           )

           UpdateImagePicker(modifier = Modifier,context,navController,criminalName.text.trim(),criminalDescription.text.trim(), id)

       }
   }
}



@Composable
fun UpdateImagePicker(modifier: Modifier = Modifier, context: Context, navController: NavHostController,name:String,description:String,id: String) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = GetContent(),
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
                .padding(bottom = 32.dp), horizontalAlignment = Alignment.CenterHorizontally,) {
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

            Button(onClick = {
                var criminalRepository = CriminalRepository(navController, context)
                criminalRepository.updateCriminalEntry(name,description,id,imageUri!!)


            }) {
                Text(text = "Update")
            }
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun UpdateProductsScreenPreview() {
    KaaChonjoTheme {
        UpdateCriminalListScreen(rememberNavController(), id = "")
    }
}


