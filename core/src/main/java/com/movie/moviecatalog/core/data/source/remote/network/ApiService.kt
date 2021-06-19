package com.movie.moviecatalog.core.data.source.remote.network

import com.movie.moviecatalog.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("3/movie/popular?api_key=756efb55af6523a917cb1265597e1ff1")
    suspend fun getPopular(): ListMovieResponse
    @GET("3/movie/upcoming?api_key=756efb55af6523a917cb1265597e1ff1")
    suspend fun getUpcoming(): ListMovieResponse
}