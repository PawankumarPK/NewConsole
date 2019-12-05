package com.project.newconsoleapp.fragment


import com.project.newconsoleapp.api.RetrofitClient
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.project.newconsoleapp.R
import com.project.newconsoleapp.api.models.StatsModel
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_arya.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

/**
 * Created by pawan on 27,November,2019
 */

class AryaFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_arya, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity.mToolbar.visibility = View.VISIBLE
        baseActivity.mToolbarTextview.text = resources.getString(R.string.arya)
        getAryaStats()
        baseActivity.mBackpress.setOnClickListener {backPress()}
    }

    private fun getAryaStats() {
        val api = RetrofitClient.apiService
        val call = api.stats()

        call.enqueue(object : Callback<StatsModel> {
            override fun onFailure(call: Call<StatsModel>?, t: Throwable?) {
                Toast.makeText(baseActivity, "Call Fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<StatsModel>?, response: Response<StatsModel>?) {
                try {
                    if (response!!.isSuccessful) {
                        val stats = response.body().data
                        for (i in stats.indices.iterator()) {

                            val mac  = stats[1].mac.toString()

                            val P = stats[1].stats.battery.P.toString()
                            val SOC = stats[1].stats.battery.SOC.toString()
                            val I = stats[1].stats.battery.I.toString()
                            val CE = stats[1].stats.battery.CE.toString()
                            val V = stats[1].stats.battery.V.toString()
                            val Screen = stats[1].stats.screens.toString().replace("[","").replace("]","")

                            mP.text = P
                            mSOC.text = SOC
                            mI.text = I
                            mCE.text = CE
                            mV.text = V
                            mMac.text = mac
                            mScreen.text = Screen
                        }
                    }
                } catch (e: Exception) {

                }

            }

        })
    }

    private fun backPress(){
        fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer,HomeFragment()).addToBackStack(null).commit()
    }


}
