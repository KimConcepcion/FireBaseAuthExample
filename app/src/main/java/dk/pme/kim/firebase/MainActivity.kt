package dk.pme.kim.firebase

import android.app.Notification
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.*
import com.google.firebase.storage.UploadTask
import java.util.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url : String = "gs://testapp-9baa2.appspot.com"
        val mobile_path = Environment.getExternalStorageDirectory().toString()+"/Download/onlinedata.txt"
        val file = Uri.fromFile(File(mobile_path))
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.getReferenceFromUrl(url)
        val onlineRef = storageRef.child("Uploads/onlinedata.txt")

		Log.i("Path", file.path)
        onlineRef.putFile(file)
                .addOnFailureListener {

                    Toast.makeText(this, "Upload unsuccesful", Toast.LENGTH_SHORT).show()
                    Log.d("MainActivity-Error", it.message)
                }
                .addOnSuccessListener {
                    Toast.makeText(this, "Upload succesful", Toast.LENGTH_SHORT).show()
                }
    }

    /*
    //  Method for registering user:
    fun performRegister(email : String, password : String){
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter email & password!", Toast.LENGTH_SHORT).show()
        }

        //  Firebase authentication to create a user with email and password:
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    if(!it.isSuccessful) return@addOnCompleteListener

                    //  else if succesful
                    Log.d("Main",
                            "Succesfully created user with uid: ${it.result?.user?.uid}")
                }
                .addOnFailureListener{
                    //  Inform user about wrong format of email - e.g. test@ is not valid
                    Toast.makeText(this, "Wrongly formatted email: ${it.message}", Toast.LENGTH_SHORT).show()
                    Log.d("Main", "Failed to create user: ${it.message}")
                }
    }*/
}


//  Test of auth - these worked so they are commented out:
//performRegister("batman@bat.com", "betterThanSuperman")
//performRegister("superman@super.com", "betterThanBatman")
//performRegister("Lasse@King.com", "betterThanJL")

