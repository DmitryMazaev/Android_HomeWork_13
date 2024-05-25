package com.example.android_homework_12.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _state = MutableStateFlow<State>(State.Initial)
    val state = _state.asStateFlow()
    init {

    }
    fun search(searchQuery: String) {
        try {
            viewModelScope.launch {
                _state.value = State.Search
                delay(3000)
                _state.value = State.Succes(searchQuery)
            }
        } catch (t: Throwable) {
            _state.value = State.Error(t.message.toString())
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}