package pl.mbui.okeapp.domain.model

import com.google.gson.annotations.SerializedName

data class ShowModel(
    @SerializedName("score")
    val score: Double,
    @SerializedName("show")
    val show: Show
)
