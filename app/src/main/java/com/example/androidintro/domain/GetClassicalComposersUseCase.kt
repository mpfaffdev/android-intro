package com.example.androidintro.domain

import com.example.androidintro.data.ClassicalComposers

class GetClassicalComposersUseCase {

    operator fun invoke() = ClassicalComposers.composers
        .map {
            val result = it.split(", ")
            Composer(
                name = result[0],
                birthDate = result.getOrNull(1)
            )
        }
}

data class Composer(
    val name: String,
    val birthDate: String?,
)
