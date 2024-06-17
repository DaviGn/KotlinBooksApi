package com.mercadolivro.application.useCases.books.get

import com.mercadolivro.application.useCases.books.BaseUseCase
import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.interfaces.ValidationStrategy
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.BookResponse
import com.mercadolivro.domain.responses.bases.IResponse
import org.springframework.stereotype.Component

@Component
class GetBookQueryUseCase(
    private val bookRepository: BookRepository,
    validators: List<ValidationStrategy<GetBookQuery>>
) : BaseUseCase<GetBookQuery, BookResponse>(validators) {
    override fun handle(request: GetBookQuery): IResponse {
        val book = bookRepository.findById(request.id);

        if (book.isEmpty)
            return notFound();

        val response = book.get().toResponse();
        return ok(response);
    }
}
