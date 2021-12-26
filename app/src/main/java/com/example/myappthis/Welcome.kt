package com.example.myappthis

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_welcome.*

class Welcome : AppCompatActivity() {
    private lateinit var db:FirebaseFirestore  //intiated variable for firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)?:return //shared preference want to learn more
        val islogin = sharedPref.getString("Email","1")   //it gets the value from shred preference
        val email = intent.getStringExtra("email")   //it gets the value from putextra


        playgame.setOnClickListener {
            val intent = Intent(this,Questionone::class.java)
            intent.putExtra("Name",tvName.text.toString())
            startActivity(intent)
            finish()
        }




        btnlogout.setOnClickListener { //for logout
            sharedPref.edit().remove("Email").apply() //when we logout we have to remove email from shared pref
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        if(islogin == "1") {     //when new comer opens the app it returns 1
            var email = intent.getStringExtra("email") //after navigation herer we get putextra value passed in main
            if(email!=null) {

                setText(email)
                with(sharedPref.edit()) {

                    putString("Email", email)
                    apply()
                }
            }else{

                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }
        }else{

           setText(islogin)

        }
    }

    private fun setText(email: String?) {
        db = FirebaseFirestore.getInstance()
        if (email != null) {
            db.collection("Quiz").document(email).get()
                .addOnSuccessListener { task->
                    tvName.text = task.get("Name").toString()
                }
        }

    }


}