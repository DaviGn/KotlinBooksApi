package com.mercadolivro.application.useCases.books.get

import com.mercadolivro.application.useCases.books.BaseUseCase
import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.interfaces.ValidationStrategy
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.mercadolivro.domain.responses.bases.NotFoundResponse
import com.mercadolivro.domain.responses.bases.OkObjectResponse
import org.springframework.stereotype.Component

@Component
class GetBookQueryUseCase(
    private val bookRepository: BookRepository,
    validators: List<ValidationStrategy<GetBookQuery>>
) : BaseUseCase<GetBookQuery>(validators) {
    override fun handle(query: GetBookQuery): IResponse {
        val book = bookRepository.findById(query.id);

        if (book.isEmpty)
            return NotFoundResponse();

        val response = book.get().toResponse();
        return OkObjectResponse(response);
    }
}
