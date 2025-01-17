package com.example.otaku.characters.adapters.info

import com.example.otaku_domain.models.characters.CharacterDetailsEntity
import com.google.errorprone.annotations.Keep

@Keep
data class ContainerCharacterInfo(
    val id: String = "container_character_info",
    val item: CharacterDetailsEntity
)