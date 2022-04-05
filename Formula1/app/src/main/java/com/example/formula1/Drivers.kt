package com.example.formula1

import com.google.gson.annotations.SerializedName
import java.util.*

data class Drivers (
    @SerializedName("permanentNumber" ) var permanentNumber : String? = null,
    @SerializedName("code"            ) var code            : String? = null,
    @SerializedName("givenName"       ) var givenName       : String? = null,
    @SerializedName("familyName"      ) var familyName      : String? = null,
    @SerializedName("dateOfBirth"     ) var dateOfBirth     : String? = null,
    @SerializedName("nationality"     ) var nationality     : String? = null
        )