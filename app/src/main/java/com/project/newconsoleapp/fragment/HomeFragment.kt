package com.project.newconsoleapp.fragment


import com.project.newconsoleapp.api.RetrofitClient
import com.project.newconsoleapp.R
import com.project.newconsoleapp.api.models.StatsModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

/**
 * Created by pawan on 27,November,2019
 */

class HomeFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity.mToolbar.visibility = View.GONE

        getOnlineBot()
        initOnlineAnimation()
        mCore0.setOnClickListener { core0() }
        mArya.setOnClickListener { arya() }
        mAmro.setOnClickListener { amro() }
    }

    private fun getOnlineBot() {
        val api = RetrofitClient.apiService
        val call = api.stats()

        call.enqueue(object : Callback<StatsModel> {
            override fun onFailure(call: Call<StatsModel>?, t: Throwable?) {
                Toast.makeText(baseActivity, "Call Failure", Toast.LENGTH_SHORT).show()
            }


            override fun onResponse(call: Call<StatsModel>?, response: Response<StatsModel>?) {
                if (response!!.isSuccessful) {
                    val onlineBot = response.body().count
                    try {
                        mOnlineBot.text = "Online Bots: $onlineBot"
                    }catch (e: Exception){

                    }

                }
            }

        })
    }

    private fun core0() {
        fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer, Core0Fragment())
            .addToBackStack(null).commit()
    }


    private fun arya() {
        fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer, AryaFragment())
            .addToBackStack(null).commit()
    }

    private fun amro() {
        fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer, AmroFragment())
            .addToBackStack(null).commit()
    }
    private fun initOnlineAnimation() {
        val animation = AlphaAnimation(1f, 0f)
        animation.duration = 1000
        animation.interpolator = LinearInterpolator() as Interpolator?
        animation.repeatCount = Animation.INFINITE
        animation.repeatMode = Animation.REVERSE
        mOnlineBot.startAnimation(animation)
    }

}
