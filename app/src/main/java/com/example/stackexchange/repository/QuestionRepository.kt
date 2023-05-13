package com.example.stackexchange.repository

import androidx.lifecycle.MutableLiveData
import com.example.stackexchange.network.StackApi
import com.example.stackexchange.model.StackDatabase
import com.example.stackexchange.model.HomeData.Home_Data
import com.example.stackexchange.networkBoundResource
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: StackApi,
    private val db: StackDatabase
) {
    private val questionsDao = db.quesDao()
    var data =MutableLiveData<Home_Data>()
    fun getCars() = networkBoundResource(

        // Query to return the list of all questions
        query = {
            questionsDao.getAllQuestions()
        },

        fetch = {
            api.getHomeData("desc","activity","stackoverflow")

                },

        saveFetchResult = {
            questionsDao.deleteAllQuestions()
            questionsDao.insertQuestions(listOf(it))
        }
    )
     suspend fun getSearchedQuestions(search:String): MutableLiveData<Home_Data> {

        try {
            var temp = api.getSearchData("desc",search,"activity","stackoverflow")
            data .value = temp
            questionsDao.deleteAllQuestions()
            questionsDao.insertQuestions(listOf(temp))
        }
        catch (e:Exception)
        {
        }
        return data
    }





}