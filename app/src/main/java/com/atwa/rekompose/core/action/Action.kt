package com.atwa.rekompose.core.action

interface Action {
    fun isComplete() = true
    object INIT : Action
    object REPLACE : Action
}