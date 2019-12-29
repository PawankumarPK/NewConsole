package com.project.newconsoleapp.api.models

import com.google.gson.annotations.SerializedName

class Battery {

    @SerializedName("V")
    var V: Int? = 0

    @SerializedName("I")
    var I: Int? = 0

    @SerializedName("P")
    var P: Int = 0

    @SerializedName("CE")
    var CE: Int? = 0

    @SerializedName("SOC")
    var SOC: Int = 0

}