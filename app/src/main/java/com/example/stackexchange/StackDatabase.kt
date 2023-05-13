package com.example.stackexchange

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.stackexchange.model.HomeData.Home_Data


@Database(entities = [Home_Data::class], version = 1)
@TypeConverters(RoomConvertors::class)
abstract class StackDatabase: RoomDatabase() {
    abstract fun quesDao(): QuestionsDAO
}