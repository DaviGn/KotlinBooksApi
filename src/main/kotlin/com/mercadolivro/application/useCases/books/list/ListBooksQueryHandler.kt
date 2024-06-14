package com.mercadolivro.application.useCases.books.list

import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.BookResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.mercadolivro.domain.responses.bases.OkObjectResponse
import com.trendyol.kediatr.QueryHandler
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class ListBooksQueryHandler(
    private val bookRepository: BookRepository
) : QueryHandler<ListBooksQuery, IResponse<Page<BookResponse>>> {
    override suspend fun handle(query: ListBooksQuery): IResponse<Page<BookResponse>> {
        val books = bookRepository.findAll(query.pagination);
        val booksResponse = books.map { it.toResponse() };
        return OkObjectResponse(booksResponse);
    }
}