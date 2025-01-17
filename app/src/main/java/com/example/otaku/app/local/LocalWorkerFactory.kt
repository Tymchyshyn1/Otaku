package com.example.otaku.app.local

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.otaku_data.utils.SharedPreferencesHelper
import com.example.otaku_domain.usecases.auth.RefreshTokenUseCase
import javax.inject.Inject
import javax.inject.Provider

class LocalWorkerFactory @Inject constructor(
    private val workerFactories: Map<Class<out ListenableWorker>, @JvmSuppressWildcards Provider<ChildWorkerFactory>>,
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val refreshTokenUseCase: RefreshTokenUseCase
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        val foundEntry =
            workerFactories.entries.find { Class.forName(workerClassName).isAssignableFrom(it.key) }
        val factoryProvider = foundEntry?.value
            ?: throw IllegalArgumentException("unknown worker class name: $workerClassName")
        return factoryProvider.get().create(appContext, workerParameters)
    }
}


class LocalWorkManagerFactory @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val refreshTokenUseCase: RefreshTokenUseCase
) : ChildWorkerFactory {
    override fun create(appContext: Context, params: WorkerParameters): ListenableWorker {
        return LocalWorker(
            appContext,
            params,
            refreshTokenUseCase = refreshTokenUseCase,
            sharedPreferencesHelper = sharedPreferencesHelper
        )
    }
}

interface ChildWorkerFactory {
    fun create(appContext: Context, params: WorkerParameters): ListenableWorker
}