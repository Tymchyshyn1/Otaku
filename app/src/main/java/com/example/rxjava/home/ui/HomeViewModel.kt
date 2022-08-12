package com.example.rxjava.home.ui

import androidx.lifecycle.*
import com.example.a16_rxjava_domain.Constants
import com.example.a16_rxjava_domain.common.Results
import com.example.a16_rxjava_domain.usecases.*
import com.example.rxjava.home.adapters.models.DisplayableItem
import com.example.rxjava.home.adapters.models.HomePosterEntity
import com.example.rxjava.home.adapters.models.HomeGenreEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAnimePrevPosterFromGenreUseCase: GetAnimePrevPosterFromGenreUseCase
) : ViewModel() {

    private val _actionError = MutableSharedFlow<String>()
    val actionError: SharedFlow<String> = _actionError

    private val _pageAnimePosterAction = MutableStateFlow<List<DisplayableItem>>(emptyList())
    val pageAnimePosterAction: StateFlow<List<DisplayableItem>> = _pageAnimePosterAction

    private val list = mutableListOf<DisplayableItem>(HomePosterEntity()).also {

        getList()

    }


    private fun getList() {
        viewModelScope.launch {

            getAnimePrevPosterActionFromGenre(Constants.ROMANTIC_ID, "Романтика")
            delay(100)
            getAnimePrevPosterActionFromGenre(Constants.SHOUNEN_ID, "Сёнен")
            delay(200)
            getAnimePrevPosterActionFromGenre(Constants.DRAMA_ID, "Драма")
            delay(300)
            getAnimePrevPosterActionFromGenre(Constants.DEMONS_ID, "Демоны")
            delay(400)
            getAnimePrevPosterActionFromGenre(Constants.SHOUJO_ID, "Сёдзё")
            delay(500)
            getAnimePrevPosterActionFromGenre(Constants.HAREM_ID, "Гарем")

        }
    }

    private fun getAnimePrevPosterActionFromGenre(genresId: Int, genreName: String) {
        viewModelScope.launch(Dispatchers.IO) {

            when (val response = getAnimePrevPosterFromGenreUseCase.execute(genresId)) {
                is Results.Success -> {
                    list.add(HomeGenreEntity(genreName, response.data))
                    _pageAnimePosterAction.value = list
                }
                is Results.Error -> {
                    list.add(HomeGenreEntity(genreName))
                    _pageAnimePosterAction.value = list
                    _actionError.emit(response.exception.message.toString())
                }
            }
        }
    }
}
