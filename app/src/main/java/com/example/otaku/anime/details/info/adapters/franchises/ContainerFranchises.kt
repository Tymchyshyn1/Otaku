package com.example.otaku.anime.details.info.adapters.franchises

import androidx.annotation.Keep
import com.example.otaku_domain.models.details.franchise.AnimeDetailsFranchisesEntity

@Keep
data class ContainerFranchises(
    val list: List<AnimeDetailsFranchisesEntity>,
    val id: String = "franchises_id"
)