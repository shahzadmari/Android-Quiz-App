package com.example.myappthis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_getuser.*

class Getuser : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db :FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getuser)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()


          btnregister.setOnClickListener {
              if (tvsignName.text.trim { it<=' ' }.toString().isNotEmpty()&&tvsignPhone.text.trim { it<=' ' }.toString().isNotEmpty()
                  && tvsignEmail.text.trim { it<=' ' }.toString().isNotEmpty()&&tvsignPassword.text.trim { it<=' ' }.toString().isNotEmpty())
              {
                  val email = tvsignEmail.text.toString()
                  val password = tvsignPassword.text.toString()
                  val name = tvsignName.text.toString()
                  val phone = tvsignPhone.text.toString()
                  val user = hashMapOf(
                      "Name" to name,
                      "Phone" to phone,
                      "Email" to email
                  )
                  val users = db.collection("Quiz") //name of db
                  val query = users.whereEqualTo("Email",email).get()
                      .addOnSuccessListener { it->
                          if(it.isEmpty){

                              auth.createUserWithEmailAndPassword(email,password)
                                  .addOnCompleteListener(this){ task->
                                      if (task.isSuccessful){

                                          users.document(email).set(user)
                                          val intent = Intent(this,Welcome::class.java)
                                          intent.putExtra("email",email)
                                          startActivity(intent)
                                          finish()
                                      }
                                      else{
                                          Toast.makeText(this,"Authentication failed",Toast.LENGTH_LONG).show()
                                      }
                                  }

                          }else{
                              Toast.makeText(this,"Your are already Registererd",Toast.LENGTH_LONG).show()
                              val intent = Intent(this,MainActivity::class.java)
                              startActivity(intent)
                          }

                      }

                      }



              }
          }
    }
