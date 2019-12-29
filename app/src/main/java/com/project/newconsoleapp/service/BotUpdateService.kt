package com.project.newconsoleapp.service

import com.project.newconsoleapp.api.RetrofitClient
import com.project.newconsoleapp.api.models.DataList
import com.project.newconsoleapp.api.models.StatsModel
import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by pawan on 27,December,2019
 */
class BotUpdateService : IntentService("BotUpdateService") {

    private fun retrofitCall() {
        val api = RetrofitClient.apiService
        val call = api.stats()

        call.enqueue(object : Callback<StatsModel> {
            override fun onFailure(call: Call<StatsModel>?, t: Throwable?) {
                Toast.makeText(this@BotUpdateService, "Service Fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<StatsModel>?, response: Response<StatsModel>?) {
                if (response!!.isSuccessful) {
                    val count = response.body().count.toString()

                    /*val mac = response.body().data
                    for (i in mac.indices) {
                       // macId = mac[i].mac
                        macId = mac[i].mac as ArrayList<DataList>
                        Log.d("===>>>", mac.toString())
                    }*/


                    val intent = Intent()
                    intent.action = "ai.jetbrain.console"
                    intent.putExtra("onlineBot", count)
                    sendBroadcast(intent)

                }

                Handler().postDelayed({
                    retrofitCall()
                }, 3000)

            }

        })

    }


    override fun onHandleIntent(p0: Intent?) {
        try {
            retrofitCall()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Thread Exception!!", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({
                retrofitCall()
            }, 5000)
        }

    }
}