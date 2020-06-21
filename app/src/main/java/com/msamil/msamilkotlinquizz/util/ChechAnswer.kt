package com.msamil.msamilkotlinquizz.util

import android.content.Context
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.msamil.msamilkotlinquizz.viewModel.QuizzShowViewModel

class CheckAnswer {
    fun checkIt(correct:String,btn:Button,c:Context,f:Fragment,url:String){
        if (correct == btn.text.toString()){
            Toast.makeText(c,"True :D",Toast.LENGTH_SHORT).show()
          var viewModel = ViewModelProviders.of(f).get(QuizzShowViewModel::class.java)
            viewModel.refreshData(url)

        }else{
            Toast.makeText(c,"Failed !!",Toast.LENGTH_SHORT).show()
        }
    }
}