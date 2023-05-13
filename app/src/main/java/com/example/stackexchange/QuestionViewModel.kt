package com.example.stackexchange

import android.util.Log
import androidx.lifecycle.*
import com.example.stackexchange.QuestionRepository
import com.example.stackexchange.model.HomeData.Home_Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    repository: QuestionRepository
) : ViewModel() {
    var word = MutableLiveData<String>()
//    var question =MutableLiveData<Resource<List<Home_Data>>>()
    var cars = repository.getCars().asLiveData()
    var  searchquestions = MutableLiveData<Home_Data>()
    var data = word.observeForever {
        Log.d("ViewModel",it)
//        repository.getSearchedQuestions(it)
//        repository.getSearchedQuestions(it).asLiveData() as MutableLiveData<Resource<List<Home_Data>>>
//        repository.getSearchedQuestions(it).asLiveData()
//        repository.getSearchedQuestions(it).asLiveData() as MutableLiveData<Resource<List<Home_Data>>>
        Log.d("asdas", searchquestions.value?.toString().toString())
        viewModelScope.launch {
             repository.getSearchedQuestions(it)
                Log.d("ASADSASDASDASD",repository.data.value.toString())
            searchquestions.postValue(repository.data.value)
        }
//        Log.d("asdas",searchquestions.value?.data.toString())
    }



}