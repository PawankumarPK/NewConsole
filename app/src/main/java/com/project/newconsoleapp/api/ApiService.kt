package ai.jetbrain.arya.api

import com.project.newconsoleapp.api.models.StatsModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/get_stats")
    fun stats(): Call<StatsModel>

}