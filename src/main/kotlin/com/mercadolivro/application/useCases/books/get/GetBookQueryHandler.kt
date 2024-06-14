package com.mercadolivro.application.useCases.books.get

import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.mercadolivro.domain.responses.bases.NotFoundResponse
import com.mercadolivro.domain.responses.bases.OkObjectResponse
import com.trendyol.kediatr.QueryHandler
import org.springframework.stereotype.Component

@Component
class GetBookQueryHandler(
    private val bookRepository: BookRepository
) : QueryHandler<GetBookQuery, IResponse> {
    override suspend fun handle(query: GetBookQuery): IResponse {
        val book = bookRepository.findById(query.id);

        if (book.isEmpty)
            return NotFoundResponse();

        val response = book.get().toResponse();
        return OkObjectResponse(response);
    }
}
