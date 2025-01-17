package com.example.otaku_domain.models.details.roles

import androidx.annotation.Keep
import com.example.otaku_domain.models.Image

@Keep
data class Person(
    val id: Int,
    val image: Image,
    val name: String,
    val russian: String,
    val url: String
)