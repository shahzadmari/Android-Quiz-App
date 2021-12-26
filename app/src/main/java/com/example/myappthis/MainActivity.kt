package com.example.myappthis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        btncreate.setOnClickListener {

            val intent = Intent(this,Getuser::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener {
            if(tvemail.text.trim{it<=' '}.toString().isNotEmpty()&&
                tvpassword.text.trim{it<=' '}.toString().isNotEmpty()){

                val email = tvemail.text.toString()
                val password = tvpassword.text.toString()

                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){ task->
                        if(task.isSuccessful){

                            val intent = Intent(this,Welcome::class.java)
                            intent.putExtra("email",email)
                            startActivity(intent)
                            finish()

                        }else{
                            Toast.makeText(this,"Wrong Email and Password",Toast.LENGTH_LONG).show()

                        }
                    }


            }else{

                Toast.makeText(this,"please Enter email and password",Toast.LENGTH_LONG).show()
            }
        }
    }
}