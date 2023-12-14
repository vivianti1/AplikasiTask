package com.d121211056.task.domain

import com.d121211056.task.data.local.GameEntity
import com.d121211056.task.data.remote.GameModel

data class GameItem(

    val id: Int,
    val title: String,
    val thumbnail: String
)

fun GameModel.toDomain()= GameItem(id = id, title = title, thumbnail = thumbnail)
fun GameEntity.toDomain()= GameItem(id = id, title = title, thumbnail = thumbnail)
