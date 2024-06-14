package com.mercadolivro.application.useCases.books.delete

import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.responses.bases.IResponse
import com.mercadolivro.domain.responses.bases.NoContentResponse
import com.trendyol.kediatr.CommandWithResultHandler
import org.springframework.stereotype.Component

@Component
class DeleteBookCommandHandler(
    private val bookRepository: BookRepository
) : CommandWithResultHandler<DeleteBookCommand, IResponse<Unit>> {
    override suspend fun handle(command: DeleteBookCommand): IResponse<Unit> {
        bookRepository.deleteById(command.id)
        return NoContentResponse();
    }
}