package com.example.androidintro.domain

import com.example.androidintro.data.ClassicalComposers
import kotlinx.coroutines.flow.flow

class GetClassicalComposersUseCase {

    operator fun invoke() = flow<List<Composer>> {
        val composers = ClassicalComposers.composers
            .map {
                val result = it.split(", ")
                Composer(
                    name = result[0],
                    birthDate = result.getOrNull(1)
                )
            }

        emit(composers)
    }
}

data class Composer(
    val name: String,
    val birthDate: String?,
)
