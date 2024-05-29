package com.example.android_homework_12.main

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_homework_12.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _state = MutableStateFlow<State>(State.Initial)
    val state = _state.asStateFlow()
    private var searchQuery: String = ""
    init {

    }
    fun search() {
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
    fun result() {

    }

    override fun onCleared() {
        super.onCleared()
    }

}