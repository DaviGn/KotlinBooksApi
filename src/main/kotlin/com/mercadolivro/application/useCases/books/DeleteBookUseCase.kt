package com.mercadolivro.application.useCases.books

import com.mercadolivro.data.repository.BookRepository
import org.springframework.stereotype.Service

data class DeleteBookUseCase(
    val id: Int
)

@Service
class DeleteBookUseCaseHandler(
    private val bookRepository: BookRepository
) {
    fun handle(data: DeleteBookUseCase) {
        bookRepository.deleteById(data.id)
    }
}