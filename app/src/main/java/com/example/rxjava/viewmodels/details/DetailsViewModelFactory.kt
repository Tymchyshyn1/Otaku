package com.example.rxjava.viewmodels.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a16_rxjava_domain.usecases.*

class DetailsViewModelFactory(
    private val getAnimeDetailsFromIdUseCase: GetAnimeDetailsFromIdUseCase,
    private val getAnimeScreenshotsFromIdUseCase: GetAnimeScreenshotsFromIdUseCase,
    private val getAnimeFranchisesFromIdUseCase: GetAnimeFranchisesFromIdUseCase,
    private val getAnimeRolesFromIdUseCase: GetAnimeRolesFromIdUseCase
) : ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(
            getAnimeDetailsFromIdUseCase,
            getAnimeScreenshotsFromIdUseCase,
            getAnimeFranchisesFromIdUseCase,
            getAnimeRolesFromIdUseCase
        ) as T
    }
}