package com.fave.assignment.data.model.detail

import com.google.gson.annotations.SerializedName

data class SpokenLanguage(

    @SerializedName("iso_639_1")
    var iso6391: String? = null,
    @SerializedName("name")
    var name: String? = null

)
