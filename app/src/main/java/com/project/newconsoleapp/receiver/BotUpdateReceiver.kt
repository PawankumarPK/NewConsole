package com.project.newconsoleapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.os.Bundle
import com.project.newconsoleapp.fragment.HomeFragment


/**
 * Created by pawan on 27,December,2019
 */
class BotUpdateReceiver : BroadcastReceiver() {

    var loadedFragment: HomeFragment? = null

    fun setFragment(fragment: HomeFragment) {
        loadedFragment = fragment
    }

    override fun onReceive(p0: Context?, intent: Intent?) {

        val bot = intent!!.getStringExtra("onlineBot")
  //      val macId= intent.getStringExtra("macId")
//        Log.d("---->>>",macId.toString())

        if (loadedFragment != null) {
            loadedFragment!!.onlineBotFunction(bot.toString())
            //loadedFragment!!.macIdFunction()

        }

    }
}
