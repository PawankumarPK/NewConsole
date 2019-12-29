package com.project.newconsoleapp.activity

import com.project.newconsoleapp.R
import com.project.newconsoleapp.api.RetrofitClient
import com.project.newconsoleapp.fragment.HomeFragment
import com.project.newconsoleapp.receiver.BotUpdateReceiver
import com.project.newconsoleapp.service.BotUpdateService
import com.project.newconsoleapp.utils.Helper
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


/**
 * Created by pawan on 27,November,2019
 */

class BaseActivity : AppCompatActivity() {

    private val receiver = BotUpdateReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitClient.init(Helper.getConfigValue(this, "api_url")!!)
        loadFragment(HomeFragment())

        Intent(this, BotUpdateService::class.java).also { intent ->
            startService(intent)
        }
    }


    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)

    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, IntentFilter("ai.jetbrain.console"))
    }


    private fun loadFragment(fragment: HomeFragment) {
        receiver.setFragment(fragment)
        supportFragmentManager.beginTransaction()
            .replace(R.id.mFrameContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}
