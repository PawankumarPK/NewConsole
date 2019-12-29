package com.project.newconsoleapp.api

import com.project.newconsoleapp.api.models.StatsModel
import com.project.newconsoleapp.api.goalModel.StdStatusModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/get_stats")
    fun stats(): Call<StatsModel>

    @GET("/set-goal-xy")
    fun setGoalXY(@Query("x") x: Float, @Query("y") y: Float, @Query("tripid") tripId: Int): Call<StdStatusModel>


}