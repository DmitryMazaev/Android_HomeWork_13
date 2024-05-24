package com.example.android_homework_12.main

sealed class State {
    object Search: State()
    object Succes: State()
}