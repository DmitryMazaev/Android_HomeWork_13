package com.example.android_homework_12.main

sealed class State {
    data object Initial: State()
    data object Search: State()
    //data class Succes(val search: String): State()
    data class Succes(val search: String): State()
    class Error(val error: String): State()
}