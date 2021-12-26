package com.example.myappthis

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_questionone.*

class Questionone : AppCompatActivity() {
    private var Name : String?=null
    private var score : Int = 0
    private var currentpostion : Int = 1
    private var questionlist : ArrayList<QuestionData>?=null
    private var seletedOption: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionone)

        SetData.getQuestion()


        Name = intent.getStringExtra("Name")

        questionlist = SetData.getQuestion() //all the questions class add herer


        setQuestion()

        one_text.setOnClickListener{

            SelectedOptionStyle(one_text,1)
        }

        two_text.setOnClickListener{

            SelectedOptionStyle(two_text,2)
        }

        three_text.setOnClickListener{

            SelectedOptionStyle(three_text,3)
        }

        four_text.setOnClickListener{

            SelectedOptionStyle(four_text,4)
        }

        btncheck.setOnClickListener {

            if(seletedOption!=0){

                val question = questionlist!![currentpostion-1]

                if(seletedOption != question.correct){

                   setColor(seletedOption,R.drawable.wrongoption)



                }else{

                    score++
                }

                setColor(question.correct,R.drawable.correct)

                if(currentpostion == questionlist!!.size) {
                    btncheck.text = "Finsih"

                }else
                    btncheck.text = "Go to Next"
            }
            else{
                currentpostion++

                    if(currentpostion<=questionlist!!.size){
                        setQuestion()
                    }
                    else{
                        var intent = Intent(this,EndingActivity::class.java)
                       intent.putExtra("Name",Name.toString())
                        intent.putExtra("Score",score.toString())
                        intent.putExtra("Totalsize",questionlist!!.size.toString())
                        startActivity(intent)
                        finish()
                    }



            }


            seletedOption = 0
        }
    }

    private fun setColor(option: Int, Color: Int) {

        when(option){

            1 -> {one_text.background=ContextCompat.getDrawable(this,Color)}
            2 -> {two_text.background=ContextCompat.getDrawable(this,Color)}
            3 -> {three_text.background=ContextCompat.getDrawable(this,Color)}
            4 -> {four_text.background=ContextCompat.getDrawable(this,Color)}

        }

    }

    private fun setQuestion() {

        var single = questionlist!![currentpostion-1] //receving 1st question information
         optionStyle()
        progress_bar.progress = currentpostion
        progress_bar.max = questionlist!!.size
        progressbar_text.text = "${currentpostion}"+"/"+"${questionlist!!.size}";


        question_text.text = single.question
        one_text.text = single.optionone
        two_text.text = single.optiontwo
        three_text.text = single.optionthree
        four_text.text = single.optionfour




    }

    private fun optionStyle(){

        val op : ArrayList<TextView> = arrayListOf()

        op.add(0,one_text)
        op.add(1,two_text)
        op.add(2,three_text)
        op.add(3,four_text)
        for (i in op) {
           i.setTextColor(Color.parseColor("#FF5C5757"))
            i.background=ContextCompat.getDrawable(this,R.drawable.questions)
            i.typeface = Typeface.DEFAULT
        }
    }

    private fun SelectedOptionStyle(view: TextView,Opt:Int){

        optionStyle()
        seletedOption = Opt
        view.background = ContextCompat.getDrawable(this,R.drawable.selectedquestion)
        view.typeface = Typeface.DEFAULT_BOLD
        view.setTextColor(Color.parseColor("#000000"))

    }

}