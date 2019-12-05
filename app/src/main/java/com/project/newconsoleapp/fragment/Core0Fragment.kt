package com.project.newconsoleapp.fragment


import com.project.newconsoleapp.api.RetrofitClient
import com.project.newconsoleapp.R
import com.project.newconsoleapp.api.models.StatsModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_core0.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

/**
 * Created by pawan on 27,November,2019
 */

class Core0Fragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_core0, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity.mToolbar.visibility = View.VISIBLE
        baseActivity.mToolbarTextview.text = resources.getString(R.string.core_0)
        baseActivity.mBackpress.setOnClickListener {backPress()}

        getCore0Stats()
    }

    private fun getCore0Stats() {
        val api = RetrofitClient.apiService
        val call = api.stats()

        call.enqueue(object : Callback<StatsModel> {
            override fun onFailure(call: Call<StatsModel>?, t: Throwable?) {
                Toast.makeText(baseActivity, "Call Fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<StatsModel>?, response: Response<StatsModel>?) {
                if (response!!.isSuccessful) {
                    try {
                        val setStats = response.body().data
                            for (i in setStats.indices) {
                                val P = setStats[0].stats.battery.P.toString()
                                val SOC = setStats[0].stats.battery.SOC.toString()
                                val Screen = setStats[1].stats.screens.toString()

                                mP.text = P
                                mSOC.text = SOC
                                mScreen.text = Screen

                            }
                    } catch (e: Exception) {
                    }
                }


            }

        })

    }

    private fun backPress(){
        fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer,HomeFragment()).addToBackStack(null).commit()
    }



}
