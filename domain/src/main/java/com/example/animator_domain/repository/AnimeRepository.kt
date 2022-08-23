package com.example.animator_domain.repository

import com.example.animator_domain.common.Results
import com.example.animator_domain.models.details.AnimeDetailsEntity
import com.example.animator_domain.models.details.franchise.AnimeDetailsFranchisesEntity
import com.example.animator_domain.models.details.roles.AnimeDetailsRolesEntity
import com.example.animator_domain.models.details.screenshots.AnimeDetailsScreenshotsEntity
import com.example.animator_domain.models.poster.AnimePosterEntity
import io.reactivex.Observable

interface AnimeRepository {

    fun getAnimePostersFromSearch(searchName: String): Observable<List<AnimePosterEntity>>

    suspend fun getAnimeDetailsFromId(id: Int): Results<AnimeDetailsEntity>

    suspend fun getAnimeScreenshotsFromId(id: Int): Results<List<AnimeDetailsScreenshotsEntity>>

    suspend fun getAnimeFranchisesFromId(id: Int): Results<List<AnimeDetailsFranchisesEntity>>

    suspend fun getAnimeRolesFromId(id: Int): Results<List<AnimeDetailsRolesEntity>>

    suspend fun getAnimePrevPostersFromGenres(genreId: Int): Results<List<AnimePosterEntity>>
}