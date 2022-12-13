package com.example.androidintro.domain

import com.example.androidintro.data.ClassicalComposers

class GetClassicalComposersUseCase {

    operator fun invoke() = ClassicalComposers.composers

}
