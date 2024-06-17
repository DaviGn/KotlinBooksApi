package com.mercadolivro.application.useCases.books.delete

import com.mercadolivro.application.useCases.books.BaseUseCaseWithoutResponse
import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.interfaces.IValidationStrategy
import com.mercadolivro.domain.responses.bases.IResponse
import org.springframework.stereotype.Component

@Component
class DeleteBookCommandUseCase(
    private val bookRepository: BookRepository,
    validators: List<IValidationStrategy<DeleteBookCommand>>
) : BaseUseCaseWithoutResponse<DeleteBookCommand>(validators) {
    override fun handle(request: DeleteBookCommand): IResponse {
        bookRepository.deleteById(request.id)
        return noContent();
    }
}