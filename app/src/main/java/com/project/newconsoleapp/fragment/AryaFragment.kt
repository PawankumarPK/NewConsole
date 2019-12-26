package com.project.newconsoleapp.fragment


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.project.newconsoleapp.R
import com.project.newconsoleapp.api.RetrofitClient
import com.project.newconsoleapp.api.models.StatsModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_arya.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Instant
import java.util.regex.Pattern


/**
 * Created by pawan on 27,November,2019
 */

class AryaFragment : BaseFragment() {

    private val TAG = "AryaFragment"
    val jsonDatePattern = Pattern.compile("/Date\\((\\d+)\\)/")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_arya, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity.mToolbar.visibility = View.VISIBLE
        baseActivity.mToolbarTextview.text = resources.getString(R.string.arya)
        getAryaStats()
        baseActivity.mBackButton.setOnClickListener { backPress() }
    }

     private fun getAryaStats() {
        val api = RetrofitClient.apiService
        val call = api.stats()

        call.enqueue(object : Callback<StatsModel> {
            override fun onFailure(call: Call<StatsModel>?, t: Throwable?) {
                toast("Response failure")
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<StatsModel>?, response: Response<StatsModel>?) {
                try {

                    if (response!!.isSuccessful) {
                        val stats = response.body().data
                        for (i in stats.indices.iterator()) {

                            val mac = stats[1].mac.toString()
                            val onlineSince = stats[1].joinstamp
                            val updateStamp = stats[1].updatestamp

                            val createdOn = "/Date($onlineSince)/"
                            val cdateMatcher = jsonDatePattern.matcher(createdOn)
                            if (cdateMatcher.matches()) {
                                val created = Instant.ofEpochMilli(java.lang.Long.parseLong(cdateMatcher.group(1)!!))
                                mOnlineSince.text = created.toString()
                            } else
                                System.err.println("Invalid format: $createdOn")


                            val updateOn = "/Date($updateStamp)/"
                            val udateMatcher = jsonDatePattern.matcher(updateOn)
                            if (udateMatcher.matches()) {
                                val created = Instant.ofEpochMilli(java.lang.Long.parseLong(udateMatcher.group(1)!!))
                                mLastUpdate.text = created.toString()

                            } else
                                System.err.println("Invalid format: $createdOn")


                            val P = stats[1].stats.battery.P.toString()
                            val SOC = stats[1].stats.battery.SOC.toString()
                            val I = stats[1].stats.battery.I.toString()
                            val CE = stats[1].stats.battery.CE.toString()
                            val V = stats[1].stats.battery.V.toString()
                            val Screen =
                                stats[1].stats.screens.toString().replace("[", "").replace("]", "")

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

    private fun backPress() {
        fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer, HomeFragment())
            .addToBackStack(null).commit()
    }


}
