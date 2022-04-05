package com.example.formula1

import com.google.gson.annotations.SerializedName

data class DriverTable(
    @SerializedName("season"  ) var season  : String?            = null,
    @SerializedName("Drivers" ) var Drivers : ArrayList<Drivers> = arrayListOf()
)
