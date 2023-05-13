package com.example.stackexchange.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stackexchange.model.HomeData.Home_Data
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionsDAO {
    @Query("SELECT * FROM questions")
    fun getAllQuestions(): Flow<List<Home_Data>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(cars: List<Home_Data>)
    @Query("DELETE FROM questions")
    suspend fun deleteAllQuestions()
}