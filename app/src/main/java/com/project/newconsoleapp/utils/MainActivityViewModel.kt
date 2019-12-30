package com.project.newconsoleapp.utils

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.newconsoleapp.objects.OnlineBots
import kotlin.random.Random

class MainActivityViewModel : ViewModel(){

    private val TAG = this.javaClass.simpleName
    private var myRandomNumber: MutableLiveData<String>? = null

    //set object of mutubale livedata
    val number: MutableLiveData<String> get() {
            Log.i("===>>>", "Get number")
            if (myRandomNumber == null) {
                myRandomNumber = MutableLiveData()
                createNumber()
            }
            return myRandomNumber as MutableLiveData<String>
        }

    fun createNumber() {
        Log.e("=>", "Create new number")
        val random = Random
        myRandomNumber!!.value = "${random.nextInt(10 - 1) + 1}"

    }

    override fun onCleared() {
        super.onCleared()
        Log.e(TAG, "ViewModel Destroyed")
    }
}
