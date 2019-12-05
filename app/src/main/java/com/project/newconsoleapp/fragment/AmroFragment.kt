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
import kotlinx.android.synthetic.main.fragment_amro.*
import kotlinx.android.synthetic.main.fragment_amro.mCE
import kotlinx.android.synthetic.main.fragment_amro.mI
import kotlinx.android.synthetic.main.fragment_amro.mP
import kotlinx.android.synthetic.main.fragment_amro.mSOC
import kotlinx.android.synthetic.main.fragment_amro.mScreen
import kotlinx.android.synthetic.main.fragment_amro.mV
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

/**
 * Created by pawan on 27,November,2019
 */

class AmroFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_amro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity.mToolbar.visibility = View.VISIBLE
        baseActivity.mToolbarTextview.text = resources.getString(R.string.amro)
        baseActivity.mBackpress.setOnClickListener {backPress()}
        getAmroStats()
    }

    private fun getAmroStats() {
        val api = RetrofitClient.apiService
        val call = api.stats()

        call.enqueue(object : Callback<StatsModel> {
            override fun onFailure(call: Call<StatsModel>?, t: Throwable?) {
                Toast.makeText(baseActivity, "Call Fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<StatsModel>, response: Response<StatsModel>) {

                try {
                    if (response.isSuccessful) {
                        val stats = response.body().data
                        for (i in stats.indices) {

                            val mac  = stats[1].mac.toString()
                            val P = stats[2].stats.battery.P.toString()
                            val SOC = stats[2].stats.battery.SOC.toString()
                            val V = stats[2].stats.battery.V.toString()
                            val I = stats[2].stats.battery.I.toString()
                            val CE = stats[2].stats.battery.CE.toString()
                            val Screen = stats[1].stats.screens.toString()
                                .replace("[","").replace("]","")

                            mMac.text = mac
                            mP.text = P
                            mSOC.text = SOC
                            mV.text = V
                            mI.text = I
                            mCE.text = CE
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
