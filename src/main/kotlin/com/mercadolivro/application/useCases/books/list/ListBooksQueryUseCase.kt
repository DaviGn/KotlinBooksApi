package com.mercadolivro.application.useCases.books.list

import com.mercadolivro.application.useCases.books.BaseUseCase
import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.interfaces.IValidationStrategy
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.BookResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.mercadolivro.domain.responses.bases.OkObjectResponse
import org.springframework.stereotype.Component

@Component
class ListBooksQueryUseCase(
    private val bookRepository: BookRepository,
    validators: List<IValidationStrategy<ListBooksQuery>>
) : BaseUseCase<ListBooksQuery, List<BookResponse>>(validators) {
    override fun handle(request: ListBooksQuery): IResponse {
        val books = bookRepository.findAll(request.pagination);
        val booksResponse = books.map { it.toResponse() };
        return OkObjectResponse(booksResponse);
    }
}