package com.example.kaachonjo.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import com.example.kaachonjo.models.User
import com.example.kaachonjo.navigation.ROUTE_HOME
import com.example.kaachonjo.navigation.ROUTE_LOGIN
import com.example.kaachonjo.navigation.ROUTE_SIGNUP
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthRepository(var navController: NavHostController, var context:Context) {
    var mAuth: FirebaseAuth
    val progress:ProgressDialog
    init {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("")
        progress.setMessage("")
    }
    fun signup(name:String, email:String, password:String){
        progress.show()
        progress.dismiss()
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            if (it.isSuccessful){
                var userData = User(name,email,password,mAuth.currentUser!!.uid)
                var regRef = FirebaseDatabase.getInstance().getReference()
                    .child("Users/"+mAuth.currentUser!!.uid)
                regRef.setValue(userData).addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(context, "Signup successful", Toast.LENGTH_SHORT).show()
                        navController.navigate(ROUTE_HOME)
                    }else{
                        Toast.makeText(context, "${it.exception!!.message}", Toast.LENGTH_SHORT)
                            .show()
                        navController.navigate(ROUTE_LOGIN)
                    }
                }
            }else{
                navController.navigate(ROUTE_SIGNUP)
            }
        }

    }
    fun login(email:String, password:String){
        progress.show()
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT)
                    .show()
                navController.navigate(ROUTE_HOME)
            }else{
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
                navController.navigate(ROUTE_LOGIN)
            }
        }

    }
    fun logout(){
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)

    }
    fun isLoggedIn():Boolean = mAuth.currentUser != null






}