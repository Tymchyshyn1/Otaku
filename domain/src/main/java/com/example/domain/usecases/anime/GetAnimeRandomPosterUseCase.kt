package com.example.domain.usecases.anime

import com.example.domain.repository.AnimeRepository
import javax.inject.Inject

class GetAnimeRandomPosterUseCase @Inject constructor(private val repository: AnimeRepository) {

    suspend fun execute(
        limit: Int = 1,
        censored: Boolean = true,
        order: String = "random"
    ) = repository.getRandomPoster(limit = limit, censored = censored, order = order)

}