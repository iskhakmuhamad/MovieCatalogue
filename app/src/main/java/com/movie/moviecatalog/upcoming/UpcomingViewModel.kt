package com.movie.moviecatalog.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.movie.moviecatalog.core.domain.usecase.MovieUseCase

class UpcomingViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val upcomings = movieUseCase.getUpcoming().asLiveData()
}