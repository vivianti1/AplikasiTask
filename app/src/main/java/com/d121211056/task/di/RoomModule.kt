package com.d121211056.task.di

import android.content.Context
import androidx.room.Room
import com.d121211056.task.data.local.GameDatabase
import com.d121211056.task.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideRoom (@ApplicationContext context: Context)=
        Room.databaseBuilder(context,GameDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideGameDao(gameDatabase: GameDatabase) = gameDatabase.getGameDao()
}