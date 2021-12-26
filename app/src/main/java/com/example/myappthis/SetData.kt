package com.example.myappthis

object SetData {

    fun getQuestion():ArrayList<QuestionData>{

        var que : ArrayList<QuestionData> = arrayListOf()
        var q1 = QuestionData(
            "which language is used to Develop Native Android Apps",
            1,
            "Java",
            "Kotlin",
            "Both",
            "None",
            3
        )

        var q2 = QuestionData(
            "The capital of Pakistan is ?",
            2,
            "Islamabad",
            "Lahore",
            "Karachi",
            "Quetta",
            1
        )

        var q3 = QuestionData(
            "Pakistan Contains?",
            3,
            "four Letters",
            "five Letters",
            "six Letters",
            "Eight Letters",
            4
        )
        var q4 = QuestionData(
            "The Developer of this App is ?",
            4,
            "Shahzad",
            "Jhon",
            "joe",
            "Williams",
            1
        )

        var q5 = QuestionData(
            "consistency is the key to ?",
            5,
            "Success",
            "failure",
            "Depression",
            "All",
            1
        )




        que.add(q1)
        que.add(q2)
        que.add(q3)
        que.add(q4)
        que.add(q5)



        return que
    }
}