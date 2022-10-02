package com.example.animator_data.repository

import com.example.animator_domain.common.Results
import com.example.animator_domain.models.details.Translation


interface WatchDataSource {
    suspend fun getSeries(malId: Long, name: String): Results<Int>

    suspend fun getVideo(
        malId: Long,
        episode: Int,
        name: String,
        kind: String
    ): Results<List<Translation>>
}

