package com.project.newconsoleapp.activity

import com.project.newconsoleapp.api.RetrofitClient
import com.project.newconsoleapp.fragment.HomeFragment
import com.project.newconsoleapp.utils.Helper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.newconsoleapp.R


/**
 * Created by pawan on 27,November,2019
 */

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        RetrofitClient.init(Helper.getConfigValue(this, "api_url")!!)

        loadFragment()
    }

    private fun loadFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.mFrameContainer,HomeFragment())
            .addToBackStack(null).commit()
    }
}
