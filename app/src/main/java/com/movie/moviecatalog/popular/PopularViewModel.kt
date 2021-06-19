package com.movie.moviecatalog.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.movie.moviecatalog.core.domain.usecase.MovieUseCase

class PopularViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getPopular().asLiveData()
}