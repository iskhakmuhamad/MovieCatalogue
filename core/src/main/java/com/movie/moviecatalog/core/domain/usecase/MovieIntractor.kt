package com.movie.moviecatalog.core.domain.usecase

import com.movie.moviecatalog.core.domain.model.Movie
import com.movie.moviecatalog.core.domain.repository.InterfaceMovieRepository

class MovieIntractor(private val movieRepository: InterfaceMovieRepository) : MovieUseCase {

    override fun getPopular() = movieRepository.getPopular()

    override fun getUpcoming() = movieRepository.getUpcoming()

    override fun getFavMovies() = movieRepository.getFavMovies()

    override fun setFavMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavMovie(movie, state)
}