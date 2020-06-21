package com.msamil.msamilkotlinquizz.sevice

import com.msamil.msamilkotlinquizz.model.Model
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface QuizAPI {
    @GET
    fun getQuiz(@Url url:String):Single<Model>
}