package com.mercadolivro.application.useCases.books.list

import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.mercadolivro.domain.responses.bases.OkObjectResponse
import com.trendyol.kediatr.QueryHandler
import org.springframework.stereotype.Component

@Component
class ListBooksQueryHandler(
    private val bookRepository: BookRepository
) : QueryHandler<ListBooksQuery, IResponse> {
    override suspend fun handle(query: ListBooksQuery): IResponse {
        val books = bookRepository.findAll(query.pagination);
        val booksResponse = books.map { it.toResponse() };
        return OkObjectResponse(booksResponse);
    }
}