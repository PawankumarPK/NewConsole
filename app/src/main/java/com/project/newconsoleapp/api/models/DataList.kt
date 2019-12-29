package com.project.newconsoleapp.api.models

import com.google.gson.annotations.SerializedName

class DataList {

    @SerializedName("stats")
    var stats: ConsoleStats = ConsoleStats()

    @SerializedName("mac")
    val mac: String? = null

    @SerializedName("joinstamp")
    var joinstamp: Long? = 0

    @SerializedName("updatestamp")
    var updatestamp: Long? = 0

}