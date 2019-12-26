package com.project.newconsoleapp.fragment


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.project.newconsoleapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_status.*
import java.time.Instant
import java.util.regex.Pattern


/**
 * Created by pawan on 27,November,2019
 */

class StatusFragment : BaseFragment() {

    val jsonDatePattern = Pattern.compile("/Date\\((\\d+)\\)/")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_status, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity.mToolbar.visibility = View.VISIBLE
        baseActivity.mToolbarTextview.text = resources.getString(R.string.fragment)

        setMacId()
        setJoinstamp()
        setLastUpdateStamp()
        setStats()
        setBattery()

        baseActivity.mBackButton.setOnClickListener { backPress() }

    }

    private fun setStats(){
        val bundle = arguments
        val stats = bundle!!.getString("stats")
        mStats.text = stats
    }

    private fun setMacId() {
        val bundle = arguments
        val mac = bundle!!.getString("mac_id")
        mMac.text = mac

    }

    private fun setBattery(){
        val bundle = arguments
        val P = bundle!!.getString("P")
        val SOC = bundle.getString("SOC")
        val V = bundle.getString("V")
        val CE = bundle.getString("CE")
        val I = bundle.getString("I")
        mP.text = P
        mSOC.text = SOC
        mV.text = V
        mCE.text = CE
        mI.text = I
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setJoinstamp() {
        val bundle = arguments
        val joinstamp = bundle!!.getString("joinstamp")
        val createdOn = "/Date($joinstamp)/"
        val cdateMatcher = jsonDatePattern.matcher(createdOn)
        if (cdateMatcher.matches()) {
            val created = Instant.ofEpochMilli(java.lang.Long.parseLong(cdateMatcher.group(1)!!))
            mOnlineSince.text = created.toString()
        } else
            System.err.println("Invalid format: $createdOn")

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setLastUpdateStamp() {
        val bundle = arguments
        val updatestamp = bundle!!.getString("updatestamp")
        val lastUpdate = "/Date($updatestamp)/"
        val cdateMatcherlast = jsonDatePattern.matcher(lastUpdate)
        if (cdateMatcherlast.matches()) {
            val created =
                Instant.ofEpochMilli(java.lang.Long.parseLong(cdateMatcherlast.group(1)!!))
            mLastUpdate.text = created.toString()
        } else
            System.err.println("Invalid format: $lastUpdate")
    }


    private fun backPress() {
        fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer, HomeFragment())
            .addToBackStack(null).commit()
    }


}