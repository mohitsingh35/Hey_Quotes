package com.ncs.heyquotes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncs.heyquotes.models.Quotes
import com.ncs.heyquotes.repository.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: QuotesRepository) : ViewModel() {


    val quotesLiveData : LiveData<List<Quotes>>
        get() = repository.quotes
    var items= emptyList<Quotes>()
    init {
        viewModelScope.launch{
            repository.getQuotes()
        }
        returnQuote()
    }
    fun returnQuote(){
        quotesLiveData.observeForever { quotes->
            items=quotes
        }

    }
}