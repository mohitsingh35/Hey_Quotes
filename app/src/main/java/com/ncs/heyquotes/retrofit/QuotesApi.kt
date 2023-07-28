package com.ncs.heyquotes.retrofit

import com.ncs.heyquotes.models.Quotes
import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {
    @GET("api/quotes")
    suspend fun getQuotes() : Response<List<Quotes>>
}