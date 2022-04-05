package com.example.formula1

import com.google.gson.annotations.SerializedName

data class MainModel(
    @SerializedName("MRData" ) var MRData : MRData? = MRData()
)
