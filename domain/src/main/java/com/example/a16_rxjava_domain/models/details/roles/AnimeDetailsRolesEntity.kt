package com.example.a16_rxjava_domain.models.details.roles

data class AnimeDetailsRolesEntity(
    val character: Character?,
    val person: Person?,
    val roles: List<String>?,
    val roles_russian: List<String>?,
    val id: String
)