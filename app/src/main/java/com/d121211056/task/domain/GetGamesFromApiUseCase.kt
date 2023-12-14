package com.d121211056.task.domain

import com.d121211056.task.data.local.toDatabase
import com.d121211056.task.repo.GameRepository
import javax.inject.Inject


class GetGamesFromApiUseCase @Inject constructor(private val gameRepository: GameRepository) {
    suspend operator fun invoke(): List<GameItem>{

        val games = gameRepository.getGamesFromApi()

        return if (games.isNotEmpty()){
            gameRepository.deleteGames()
            gameRepository.insertGames(games.map { it.toDatabase() })
            games
        }else{
            emptyList()
        }
    }
}