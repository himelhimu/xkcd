package com.himel.apps.xkcd.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himel.apps.xkcd.models.Comic
import com.himel.apps.xkcd.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiService:ApiService) : ViewModel() {

    private val _text = MutableLiveData<Comic>().apply {
        value = Comic.EMPTY
    }

    val comicData : LiveData<Comic> = _text

    fun getComicData(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            if (id==0){
                _text.postValue(apiService.getCurrentComic())
            }
        }
    }


}