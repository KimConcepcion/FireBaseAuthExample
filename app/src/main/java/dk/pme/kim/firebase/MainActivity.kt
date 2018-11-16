package dk.pme.kim.firebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  Test of auth:
        performRegister("batman@bat.com", "betterThanSuperman")
        performRegister("superman@super.com", "betterThanBatman")
        performRegister("Lasse@King.com", "betterThanJL")
    }

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
                            "Succesfully created user with uid: ${it.result.user.uid}")
                }
                .addOnFailureListener{
                    //  Inform user about wrong format of email - e.g. test@ is not valid
                    Toast.makeText(this, "Wrongly formatted email: ${it.message}", Toast.LENGTH_SHORT).show()
                    Log.d("Main", "Failed to create user: ${it.message}")
                }
    }
}


