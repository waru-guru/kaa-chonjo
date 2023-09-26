package com.example.kaachonjo.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.kaachonjo.models.Criminal
import com.example.kaachonjo.models.UserInput
import com.example.kaachonjo.navigation.ROUTE_CRIMINAL_LIST
import com.example.kaachonjo.navigation.ROUTE_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class CriminalRepository(var navController:NavHostController,var context: Context) {
    var authRepository:AuthRepository
    var progress: ProgressDialog
    init {
        authRepository = AuthRepository(navController,context)
//        if (!authRepository.isLoggedIn()){
//            navController.navigate(ROUTE_LOGIN)
//        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")


    }
    fun viewCriminal(criminal: MutableState<Criminal>, criminals: SnapshotStateList<Criminal>): SnapshotStateList<Criminal> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Criminals")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                criminals.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Criminal::class.java)
                    criminal.value = value!!
                    criminals.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return criminals
    }

    fun saveCriminal(criminalName:String, criminalDescription:String, imageUrl:String){
        var id = System.currentTimeMillis().toString()
        var criminalData = Criminal(criminalName,criminalDescription,imageUrl,id)
        var criminalRef = FirebaseDatabase.getInstance().getReference().child("Criminals/$id")
        progress.show()
        criminalRef.setValue(criminalData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context, "Data added successfully", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_CRIMINAL_LIST)
            }else{
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun deleteCriminalEntry(id:String){
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("Criminals/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context, "Criminal entry deleted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

//    fun updateCriminalEntry(name:String, description:String, image:String,id:String){
//        var updateRef = FirebaseDatabase.getInstance().getReference()
//            .child("Criminals/$id")
//        progress.show()
//        var updateData = Criminal(name, description, image, id)
//        updateRef.setValue(updateData).addOnCompleteListener {
//            progress.dismiss()
//            if (it.isSuccessful){
//                Toast.makeText(context, "Criminal Update successful", Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    fun saveCriminalWithImage(criminalName:String, criminalDescription: String, filePath: Uri){
        var id = System.currentTimeMillis().toString()
        var storageReference = FirebaseStorage.getInstance().getReference().child("Criminals/$id")
        progress.show()

        storageReference.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var houseData = Criminal(criminalName,criminalDescription,imageUrl,id)
                    var dbRef = FirebaseDatabase.getInstance()
                        .getReference().child("Criminals/$id")
                    dbRef.setValue(houseData)
                    Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun viewCriminalWithImage(criminal:MutableState<Criminal>, criminals:SnapshotStateList<Criminal>): SnapshotStateList<Criminal> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Criminals")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                criminals.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Criminal::class.java)
                    criminal.value = value!!
                    criminals.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return criminals
    }
    fun saveUserInputWithImage(corruptActivityDesc:String, filePath: Uri){
        var id = System.currentTimeMillis().toString()
        var storageReference = FirebaseStorage.getInstance().getReference().child("UserInputs/$id")
        progress.show()

        storageReference.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var houseData = UserInput(corruptActivityDesc,imageUrl,id)
                    var dbRef = FirebaseDatabase.getInstance()
                        .getReference().child("UserInputs/$id")
                    dbRef.setValue(houseData)
                    Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun viewUserInputWithImage(criminal:MutableState<Criminal>, criminals:SnapshotStateList<Criminal>): SnapshotStateList<Criminal> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Criminals")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                criminals.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Criminal::class.java)
                    criminal.value = value!!
                    criminals.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return criminals
    }

    fun updateCriminalEntry(name:String, description: String, id: String, filePath: Uri){
        var storageReference = FirebaseStorage.getInstance().getReference().child("Criminals/$id")
        progress.show()

        storageReference.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var houseData = Criminal(name,description, imageUrl, id)
                    var dbRef = FirebaseDatabase.getInstance()
                        .getReference().child("Criminals/$id")
                    dbRef.setValue(houseData)
                    Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }


    }



}