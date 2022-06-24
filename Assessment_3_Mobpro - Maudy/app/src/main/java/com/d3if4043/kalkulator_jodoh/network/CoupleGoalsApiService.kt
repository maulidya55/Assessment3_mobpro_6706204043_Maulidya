package com.d3if4043.kalkulator_jodoh.network

import com.d3if4043.kalkulator_jodoh.model.CoupleGoals
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CoupleGoalsApiService {

    @GET("couple-goals.json")
    fun getCoupleGoals() : Call<List<CoupleGoals>>

    companion object {

        var BASE_URL = "https://raw.githubusercontent.com/maulidya55/ImplementasiAlgoritma/main/"

        fun create() : CoupleGoalsApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(CoupleGoalsApiService::class.java)

        }
    }
}