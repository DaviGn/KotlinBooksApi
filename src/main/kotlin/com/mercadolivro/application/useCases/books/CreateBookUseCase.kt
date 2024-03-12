package com.mercadolivro.application.useCases.books

import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.mappers.toBookModel
import com.mercadolivro.domain.requests.CreateBookRequest
import org.springframework.stereotype.Service

data class CreateBookUseCase (
    val request: CreateBookRequest
)

@Service
class CreateBookUseCaseHandler(
    private val bookRepository: BookRepository
) {
    fun handle(data: CreateBookUseCase){
        val book = data.request.toBookModel();
        bookRepository.save(book);
    }
}