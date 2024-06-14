package com.mercadolivro.application.useCases.books.delete

import com.mercadolivro.application.useCases.books.BaseUseCase
import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.interfaces.ValidationStrategy
import com.mercadolivro.domain.responses.bases.IResponse
import com.mercadolivro.domain.responses.bases.NoContentResponse
import org.springframework.stereotype.Component

@Component
class DeleteBookCommandUseCase(
    private val bookRepository: BookRepository,
    validators: List<ValidationStrategy<DeleteBookCommand>>
) : BaseUseCase<DeleteBookCommand>(validators) {
    override fun handle(command: DeleteBookCommand): IResponse {
        bookRepository.deleteById(command.id)
        return NoContentResponse();
    }
}