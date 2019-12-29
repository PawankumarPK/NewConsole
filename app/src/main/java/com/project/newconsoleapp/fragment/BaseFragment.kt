package com.project.newconsoleapp.fragment

import com.project.newconsoleapp.R
import com.project.newconsoleapp.activity.BaseActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment


/**
 * Created by pawan on 27,November,2019
 */

open class BaseFragment : Fragment() {

    lateinit var baseActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseActivity = activity as BaseActivity
    }

     fun toast(msg : Any){
        Toast.makeText(baseActivity,msg.toString(),Toast.LENGTH_SHORT).show()
    }

}