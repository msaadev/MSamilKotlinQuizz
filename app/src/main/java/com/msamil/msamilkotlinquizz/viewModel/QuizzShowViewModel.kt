package com.msamil.msamilkotlinquizz.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.msamil.msamilkotlinquizz.model.Model
import com.msamil.msamilkotlinquizz.sevice.QuizAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class QuizzShowViewModel(application: Application) : BaseViewModel(application)  {

    private val quizzApiService = QuizAPIService()
    private val disposable = CompositeDisposable()

    val questionData = MutableLiveData<Model>()
    val questionError = MutableLiveData<Boolean>()
    val questionLoading = MutableLiveData<Boolean>()


    fun refreshData (url:String){
       getDataFromApi(url)
    }

    private fun getDataFromApi(url:String){
        questionLoading.value = true
        disposable.add(
            quizzApiService.getData(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Model>(){
                    override fun onSuccess(t: Model) {
                       questionData.value = t
                        questionLoading.value = false
                        questionError.value = false
                    }

                    override fun onError(e: Throwable) {
                        questionLoading.value = false
                        questionError.value = true
                    }

                })
        )

    }
}