package com.example.projectbasesetup.models

import com.example.projectbasesetup.models.Breed
import com.example.projectbasesetup.models.Category

data class CatsDataModel(
    val breeds: List<Breed>,
    val categories: List<Category>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)