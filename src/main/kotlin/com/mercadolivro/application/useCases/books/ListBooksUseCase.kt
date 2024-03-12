package com.mercadolivro.application.useCases.books

import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.BookResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable

data class ListBooksUseCase(
        val pagination: Pageable
)

@Service
class ListBooksUseCaseHandler(
        private val bookRepository: BookRepository
) {
    fun handle(data: ListBooksUseCase): Page<BookResponse> {
        val books = bookRepository.findAll(data.pagination);
        val booksResponse = books.map { it.toResponse() };
        return booksResponse;
    }
}