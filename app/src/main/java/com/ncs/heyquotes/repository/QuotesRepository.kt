package com.ncs.heyquotes.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ncs.heyquotes.models.Quotes
import com.ncs.heyquotes.retrofit.QuotesApi
import java.io.IOException
import javax.inject.Inject

class QuotesRepository @Inject constructor(private val quotesApi: QuotesApi) {

    private val quotesLiveData=MutableLiveData<List<Quotes>>()
    val quotes:LiveData<List<Quotes>>
        get() = quotesLiveData


    suspend fun getQuotes(){

        val result=quotesApi.getQuotes()
        if(result.isSuccessful && result.body() != null) {
            quotesLiveData.postValue(result.body()!!)
        }
    }


}