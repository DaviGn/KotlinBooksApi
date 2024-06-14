package com.mercadolivro.application.useCases.books.update

import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.mappers.toBookModel
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.BookResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.mercadolivro.domain.responses.bases.NotFoundResponse
import com.mercadolivro.domain.responses.bases.OkObjectResponse
import com.trendyol.kediatr.CommandWithResultHandler
import org.springframework.stereotype.Component

@Component
class UpdateBookCommandHandler(
    private val bookRepository: BookRepository
) : CommandWithResultHandler<UpdateBookCommand, IResponse<BookResponse>> {
    override suspend fun handle(command: UpdateBookCommand): IResponse<BookResponse> {
        val originalBook = bookRepository.findById(command.id);

        if (originalBook.isEmpty)
            return NotFoundResponse();

        val updatedBook = command.request.toBookModel(originalBook.get());
        bookRepository.save(updatedBook);
        val response = updatedBook.toResponse();
        return OkObjectResponse(response);
    }
}