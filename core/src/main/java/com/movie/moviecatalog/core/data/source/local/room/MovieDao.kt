package com.movie.moviecatalog.core.data.source.local.room

import androidx.room.*
import com.movie.moviecatalog.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies WHERE upcoming = 0")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE upcoming = 1")
    fun getUpcoming(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE fav = 1")
    fun getFavMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie: List<MovieEntity>)

    @Update
    fun updateFavMovie(movie: MovieEntity)
}