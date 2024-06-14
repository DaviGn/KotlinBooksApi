package com.mercadolivro.application.useCases.books.list

import com.mercadolivro.application.useCases.books.BaseUseCase
import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.interfaces.ValidationStrategy
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.mercadolivro.domain.responses.bases.OkObjectResponse
import org.springframework.stereotype.Component

@Component
class ListBooksQueryUseCase(
    private val bookRepository: BookRepository,
    validators: List<ValidationStrategy<ListBooksQuery>>
) : BaseUseCase<ListBooksQuery>(validators) {
    override fun handle(query: ListBooksQuery): IResponse {
        val books = bookRepository.findAll(query.pagination);
        val booksResponse = books.map { it.toResponse() };
        return OkObjectResponse(booksResponse);
    }
}