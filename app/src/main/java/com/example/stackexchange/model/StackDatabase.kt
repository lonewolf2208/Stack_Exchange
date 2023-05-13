package com.example.stackexchange.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.stackexchange.model.HomeData.Home_Data
import com.example.stackexchange.utils.RoomConvertors


@Database(entities = [Home_Data::class], version = 1)
@TypeConverters(RoomConvertors::class)
abstract class StackDatabase: RoomDatabase() {
    abstract fun quesDao(): QuestionsDAO
}