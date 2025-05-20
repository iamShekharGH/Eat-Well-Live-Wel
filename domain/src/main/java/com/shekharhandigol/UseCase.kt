package com.shekharhandigol

interface UseCase<In, Out> {
    suspend fun invoke(input: In): Out
}