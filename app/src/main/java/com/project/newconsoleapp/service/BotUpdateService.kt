package com.project.newconsoleapp.service

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.project.newconsoleapp.api.RetrofitClient
import com.project.newconsoleapp.api.models.StatsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by pawan on 27,December,2019
 */
class BotUpdateService : IntentService("BotUpdateService") {

    var macId: String? = null

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
                    val mac = response.body().data
                    for (i in mac.indices) {
                        macId = mac[i].mac
                        Log.d("===>>>", macId!!)
                    }


                    val intent = Intent()
                    intent.action = "ai.jetbrain.console"
                    intent.putExtra("onlineBot", count)
                    intent.putExtra("macId", macId)
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