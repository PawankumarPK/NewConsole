package com.project.newconsoleapp.api.models

import com.google.gson.annotations.SerializedName

class Stats {

    @SerializedName("battery")
    var battery : Battery = Battery()

    @SerializedName("screens")
    var screens : ArrayList<String> = ArrayList()
}