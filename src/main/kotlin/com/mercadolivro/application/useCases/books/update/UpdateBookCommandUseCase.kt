package com.mercadolivro.application.useCases.books.update

import com.mercadolivro.application.useCases.books.BaseUseCase
import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.interfaces.ValidationStrategy
import com.mercadolivro.domain.mappers.toBookModel
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.mercadolivro.domain.responses.bases.NotFoundResponse
import com.mercadolivro.domain.responses.bases.OkObjectResponse
import org.springframework.stereotype.Component

@Component
class UpdateBookCommandUseCase(
    private val bookRepository: BookRepository,
    validators: List<ValidationStrategy<UpdateBookCommand>>
) : BaseUseCase<UpdateBookCommand>(validators) {
    override fun handle(command: UpdateBookCommand): IResponse {
        val validationResult = this.runValidations(command)

        if (validationResult.failed) {
            return returnValidationErrors(validationResult)
        }

        val originalBook = bookRepository.findById(command.id);

        if (originalBook.isEmpty)
            return NotFoundResponse();

        val updatedBook = command.request.toBookModel(originalBook.get());
        bookRepository.save(updatedBook);
        val response = updatedBook.toResponse();
        return OkObjectResponse(response);
    }
}