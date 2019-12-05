package com.project.newconsoleapp.api.models

import com.google.gson.annotations.SerializedName

class StatsModel {

    @SerializedName("count")
    var count : Int = 0

    @SerializedName("data")
    var data : ArrayList<DataList> = ArrayList()
}