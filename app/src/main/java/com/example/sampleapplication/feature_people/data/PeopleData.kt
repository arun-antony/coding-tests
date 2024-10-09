package com.example.sampleapplication.feature_people.data

import com.google.gson.annotations.SerializedName


data class PeopleData(
    var count: Int,
    var next: String,
    var previous: String?,
    var results: List<PeopleItem>
)

data class PeopleItem(
    val name: String,

    @SerializedName("birth_year")
    val birthYear: String
)


fun PeopleItem.getSample(value: String = "hell"){

}

