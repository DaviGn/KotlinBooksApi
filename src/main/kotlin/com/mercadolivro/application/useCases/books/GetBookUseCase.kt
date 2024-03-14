package com.mercadolivro.application.useCases.books

import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.exceptions.NotFoundException
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.BookResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.mercadolivro.domain.responses.bases.NotFoundResponse
import com.mercadolivro.domain.responses.bases.OkObjectResponse
import org.springframework.stereotype.Service

data class GetBookUseCase(
    val id: Int
)

@Service
class GetBookUseCaseHandler(
    private val bookRepository: BookRepository
) {
    fun handle(data: GetBookUseCase): IResponse<BookResponse> {
        val book = bookRepository.findById(data.id);

        if (book.isEmpty)
            return NotFoundResponse();

        val response = book.get().toResponse();
        return OkObjectResponse(response);
    }
}
