package com.project.newconsoleapp.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.project.newconsoleapp.activity.BaseActivity


/**
 * Created by pawan on 27,November,2019
 */

open class BaseFragment : Fragment() {

    lateinit var baseActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseActivity = activity as BaseActivity
    }

    fun toast(msg: Any) {
        Toast.makeText(baseActivity, msg.toString(), Toast.LENGTH_SHORT).show()
    }

}