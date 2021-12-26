package com.example.myappthis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_ending.*
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.jar.Attributes

class EndingActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ending)

        val username = intent.getStringExtra("Name")
        val Score =  intent.getStringExtra("Score")
        val total = intent.getStringExtra("Totalsize")

        finish.setOnClickListener {

            var intent = Intent(this,Welcome::class.java)
            startActivity(intent)
            finish()

        }


          if(Score!!.toInt()<= 2){
            tvcong.text = "Bad Luck"
            tvscore.text = "Score : $Score / $total"
            tvEndName.text = username
              Glide.with(this).load(R.drawable.saddy).into(mygif)

              }else{

              tvcong.text = "Congratulations"
              tvscore.text = "$Score / $total"
              tvEndName.text = username


          }






          /* when(Score) {
               "1" -> {
                   tvcong.text = "Bad Luck"
                   tvscore.text = "$Score / $total"
                   tvName.text = username.toString()
               }
               "2" -> {
                   tvcong.text = "Bad Luck"
                   tvscore.text = "$Score / $total"
                   tvName.text = username.toString()
               }
               "3" -> {
                   tvcong.text = "Bad Luck"
                   tvscore.text = "$Score / $total"
                   tvName.text = username.toString()
               }
               else -> {

                   tvcong.text = "Congratulations"
                   tvscore.text = "$Score / $total"
                   tvName.text = username.toString()
               }
           }*/

    }
}