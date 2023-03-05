package io.wvd.catgpt.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.wvd.catgpt.R
import io.wvd.catgpt.data.Cat
import io.wvd.catgpt.data.CatsRepo
import io.wvd.catgpt.data.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DataStoreRepository) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing.asStateFlow()

    private val _inputText = MutableStateFlow("")
    val inputText: StateFlow<String> get() = _inputText.asStateFlow()

    private val _inputData = MutableStateFlow(listOf<String>())
    val inputData: StateFlow<List<String>> get() = _inputData.asStateFlow()

    var cats by mutableStateOf(CatsRepo.getCats())


    fun refresh() {

        viewModelScope.launch(Dispatchers.IO) {
            _isRefreshing.emit(true)
            delay(1500)
            Log.d("BILLY", "REFRESH")
            resetInputText()
            _isRefreshing.emit(false)
        }

    }

    fun setInputText(inputText: String) {
        _inputText.value = inputText
    }

    fun resetInputText(){
        _inputText.value = ""
    }

    fun addInputData(inputText: String) {
        _inputData.value = listOf(inputText)
    }

    fun resetInputData(){
        _inputText.value = ""
    }

    fun addUser(cat: Cat) {
        cats = cats + listOf(cat)
    }

    fun addCat(cat: Cat) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1500)
            cats = cats + listOf(cat)
        }
    }

    fun removeCat(cat: Cat) {
        cats = cats.toMutableList().also {
            it.remove(cat)
        }
    }

    fun showUserInput(text: String): Cat {
        return Cat(name = text, R.drawable.avatar_user)
    }

}