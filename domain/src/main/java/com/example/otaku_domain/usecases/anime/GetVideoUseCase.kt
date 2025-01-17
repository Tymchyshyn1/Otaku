package com.example.otaku_domain.usecases.anime

import com.example.otaku_domain.repository.AnimeRepository
import javax.inject.Inject

class GetVideoUseCase @Inject constructor(private val repository: AnimeRepository) {

    suspend fun execute(
        malId: Long,
        episode: Int,
        name: String,
        kind: String
    ) = repository.getVideo(malId = malId, episode = episode, name = name, kind = kind)
}