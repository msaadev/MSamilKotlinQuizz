package com.msamil.msamilkotlinquizz.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.msamil.msamilkotlinquizz.R
import com.msamil.msamilkotlinquizz.model.Results
import com.msamil.msamilkotlinquizz.util.CheckAnswer
import com.msamil.msamilkotlinquizz.viewModel.QuizzShowViewModel
import kotlinx.android.synthetic.main.fragment_quizz_show.*
import java.util.*
import kotlin.collections.ArrayList

class QuizzShow : Fragment() {

    private lateinit var viewModel : QuizzShowViewModel
    private var a1 = ""
    private var a2 = ""
    private var a3 = ""
    private var a4 = ""

    private lateinit var data :List<Results>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quizz_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val db = context?.getSharedPreferences("com.msamil.kotlinquizz",Context.MODE_PRIVATE)
        val URL = db?.getString("url","Fail")


        viewModel = ViewModelProviders.of(this).get(QuizzShowViewModel::class.java)
        viewModel.refreshData(URL!!)
        observeLiveData()
        aBtn.setOnClickListener {CheckAnswer().checkIt(a1,aBtn,it.context,this,URL) }
        bBtn.setOnClickListener {CheckAnswer().checkIt(a1,bBtn,it.context,this,URL) }
        cBtn.setOnClickListener {CheckAnswer().checkIt(a1,cBtn,it.context,this,URL) }
        dBtn.setOnClickListener {CheckAnswer().checkIt(a1,dBtn,it.context,this,URL) }
        }

    fun observeLiveData(){
        viewModel.questionData.observe(viewLifecycleOwner, Observer { question ->
            question?.let {

                data = it.results


               text2.setText(data[0].question)

                 a1 = data[0].correct_answer
                println(a1)
                 a2 = data[0].incorrect_answers[0]
                 a3 = data[0].incorrect_answers[1]
                 a4 = data[0].incorrect_answers[2]

                var myArray = ArrayList<String>()
                myArray.add(a1)
                myArray.add(a3)
                myArray.add(a2)
                myArray.add(a4)

                Collections.shuffle(myArray)
                aBtn.setText(myArray[3])
                bBtn.setText(myArray[0])
                cBtn.setText(myArray[1])
                dBtn.setText(myArray[2])



                questionScroll.visibility = View.VISIBLE
                errorShow.visibility = View.INVISIBLE
                loadingShow.visibility = View.INVISIBLE
            }
        })


        viewModel.questionLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it){
                    questionScroll.visibility = View.INVISIBLE
                    errorShow.visibility = View.INVISIBLE
                    loadingShow.visibility = View.VISIBLE
                }
            }
        })
    }

}