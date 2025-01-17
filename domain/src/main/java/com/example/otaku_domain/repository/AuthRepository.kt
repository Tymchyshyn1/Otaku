package com.example.otaku_domain.repository

import com.example.otaku_domain.common.Results
import com.example.otaku_domain.models.Token

interface AuthRepository {

    suspend fun signIn(authCode: String): Results<Token>

    suspend fun refreshToken(refreshToken: String): Results<Token>

}