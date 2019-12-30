package com.project.newconsoleapp.receiver

import com.project.newconsoleapp.fragment.HomeFragment
import com.project.newconsoleapp.objects.OnlineBots
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.os.Bundle



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
        OnlineBots.onlineBot  = bot

        if (loadedFragment != null) {
           // loadedFragment!!.onlineBotFunction(bot!!.toString())

        }

    }
}
