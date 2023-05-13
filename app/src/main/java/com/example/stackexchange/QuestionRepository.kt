package com.example.stackexchange

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.stackexchange.model.HomeData.Home_Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: StackApi,
    private val db: StackDatabase
) {
    private val carsDao = db.quesDao()
    var data =MutableLiveData<Home_Data>()
    fun getCars() = networkBoundResource(

        // Query to return the list of all cars
        query = {
            carsDao.getAllQuestions()
        },

        // Just for testing purpose,
        // a delay of 2 second is set.
        fetch = {
            api.getHomeData("desc","activity","stackoverflow")

                },

        // Save the results in the table.
        // If data exists, then delete it
        // and then store.
        saveFetchResult = {
                carsDao.deleteAllQuestions()
                carsDao.insertQuestions(listOf(it))
        }
    )
     suspend fun getSearchedQuestions(search:String): MutableLiveData<Home_Data> {

        try {
            var temp = api.getSearchData("desc",search,"activity","stackoverflow")
//            Log.d("Repo",Resource.Success(temp).toString())
            data .value = temp
            carsDao.deleteAllQuestions()
            carsDao.insertQuestions(listOf(temp))

        }
        catch (e:Exception)
        {
//            data.postValue(Resource.Error(e))
        }


        return data
    }





}