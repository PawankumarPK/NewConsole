package com.project.newconsoleapp.api.models

import com.google.gson.annotations.SerializedName

class DataList {

    @SerializedName("stats")
    var stats: Stats = Stats()

    @SerializedName("mac")
    val mac : String? = null

    /*@SerializedName("joinstamp")
    var joinstamp: Int? = 0

    @SerializedName("updatestamp")
    var updatestamp: Int? = 0

    */
}