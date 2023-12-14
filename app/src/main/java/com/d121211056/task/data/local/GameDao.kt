package com.d121211056.task.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(game: List<GameEntity>)

    @Query("DELETE FROM game_table")
    suspend fun deleteGames()

    @Query("SELECT * FROM game_table ORDER BY title DESC")
    suspend fun getGames(): List<GameEntity>
}