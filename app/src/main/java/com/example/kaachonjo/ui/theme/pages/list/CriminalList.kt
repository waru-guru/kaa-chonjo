package com.example.kaachonjo.ui.theme.pages.list

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.kaachonjo.data.AuthRepository
import com.example.kaachonjo.data.CriminalRepository
import com.example.kaachonjo.models.Criminal
import com.example.kaachonjo.navigation.ROUTE_LOGIN
import com.example.kaachonjo.navigation.ROUTE_SUCCESS
import com.example.kaachonjo.navigation.ROUTE_UPDATE_CRIMINAL_LIST
import com.example.kaachonjo.ui.theme.Backg
import com.example.kaachonjo.ui.theme.KaaChonjoTheme
import com.example.kaachonjo.ui.theme.Others
import com.example.kaachonjo.ui.theme.Top
import com.example.kaachonjo.ui.theme.amaticFontFamily
import com.example.kaachonjo.ui.theme.luckiestGuyFamily
import com.example.kaachonjo.ui.theme.robotoFontFamily

@Composable
fun CriminalListScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .background(Backg)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var criminalRepository = CriminalRepository(navController, context)


        val emptyCriminalState = remember { mutableStateOf(Criminal("","","","")) }
        var emptyCriminalListState = remember { mutableStateListOf<Criminal>() }

        var criminals = criminalRepository.viewCriminalWithImage(emptyCriminalState, emptyCriminalListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Criminal list",
                fontSize = 20.sp,
                fontFamily = luckiestGuyFamily,
                color = Others)

            Spacer(modifier = Modifier.height(20.dp))

           Row(
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier.fillMaxWidth()
           ){
               Text(text = "Which one did you spot?",
                   fontSize = 30.sp,
                   fontFamily = amaticFontFamily,
                   color = Color.White
               )
           }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxSize()

            ){
                items(criminals){
                    CriminalItem(
                        name = it.name,
                        description = it.description,
                        imageUrl = it.imageUrl,
                        id = it.id,
                        navController = navController,
                        criminalRepository = criminalRepository
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}



@Composable
fun CriminalItem(name:String, description:String, imageUrl:String, id:String,
                 navController:NavHostController, criminalRepository: CriminalRepository) {
    var context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Top)) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Column {


                Text(
                    text = name,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Others
                )
                Text(text = description, fontSize = 16.sp, color = Color.White)

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    IconButton(
                        onClick = {
                            var authRepository = AuthRepository(navController, context)
                            if (!(authRepository.isLoggedIn())) {
                                navController.navigate(ROUTE_LOGIN)
                            } else {
                                criminalRepository.deleteCriminalEntry(id)
                            }
                        },
                        //modifier = Modifier.align(Alignment.End),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Backg,
                            contentColor = Others
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete Icon"
                        )
                    }
                    IconButton(
                        onClick = {
                            var authRepository = AuthRepository(navController, context)
                            if (!(authRepository.isLoggedIn())) {
                                navController.navigate(ROUTE_LOGIN)
                            } else {
                                navController.navigate("$ROUTE_UPDATE_CRIMINAL_LIST/$id")
                            }
                        },
                        //modifier = Modifier.align(Alignment.End),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Backg,
                            contentColor = Others
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "Update Icon"
                        )

                    }
                    Text(
                        text = "Report",
                        color = Color.White,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Backg)
                            .padding(10.dp,5.dp)
                            .clickable {
                                val uri = Uri.parse("smsto:0115242320")
                                val intent = Intent(Intent.ACTION_SENDTO, uri)
                                intent.putExtra("sms_body", "A wanted criminal has been spotted. Please contact this number to get more information.")
                                context.startActivity(intent)
                                navController.navigate(ROUTE_SUCCESS)
                            }
                    )

                }
            }
        }

    }
}



@Preview
@Composable
fun CriminalListScreenPreview() {
    KaaChonjoTheme {
        CriminalListScreen(rememberNavController())
    }


}