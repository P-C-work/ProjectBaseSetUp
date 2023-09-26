package com.example.projectbasesetup.models

data class VoteSuccessModel(
    val country_code: String,
    val id: Int,
    val image_id: String,
    val message: String,
    val sub_id: String,
    val value: Int
)