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
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.newconsoleapp.adapter.RecyclerViewAdapter
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

    private val TAG = "HomeFragment"
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity.mToolbar.visibility = View.GONE

        loadRecyclerView()
        retrofitCallbacks()
        onlineBotAnimation()
    }

    private fun loadRecyclerView() {
        adapter = RecyclerViewAdapter()
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(baseActivity)
    }

    private fun retrofitCallbacks() {
        val api = RetrofitClient.apiService
        val call = api.stats()

        call.enqueue(object : Callback<StatsModel> {
            override fun onFailure(call: Call<StatsModel>?, t: Throwable?) {
                toast("Response failure")
            }

            override fun onResponse(call: Call<StatsModel>?, response: Response<StatsModel>?) {
                if (response!!.isSuccessful) {
                    val dataList = response.body().data
                    adapter.getDataIntoItemlist(dataList)
                    adapter.notifyDataSetChanged()

                    val onlineBot = response.body().count
                    mOnlineBot.text = "Online Bots: $onlineBot"

                }
            }

        })
    }

    private fun onlineBotAnimation() {
        val animation = AlphaAnimation(1f, 0f)
        animation.duration = 1000
        animation.interpolator = LinearInterpolator() as Interpolator?
        animation.repeatCount = Animation.INFINITE
        animation.repeatMode = Animation.REVERSE
        mOnlineBot.startAnimation(animation)
    }

}
