package pl.mbui.okeapp.domain.model

import com.google.gson.annotations.SerializedName

data class Show(
    @SerializedName("id")
    val id: Int,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("image")
    val image: Image?,
    @SerializedName("name")
    val name: String,
)