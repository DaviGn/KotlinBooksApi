package com.mercadolivro.application.useCases.books.create

import com.mercadolivro.application.kafka.producers.BookProducer
import com.mercadolivro.application.useCases.books.BaseUseCase
import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.interfaces.ValidationStrategy
import com.mercadolivro.domain.mappers.toBookModel
import com.mercadolivro.domain.mappers.toEvent
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.bases.CreatedResponse
import com.mercadolivro.domain.responses.bases.IResponse
import org.springframework.stereotype.Component

@Component
class CreateBookCommandUseCase(
    private val bookRepository: BookRepository,
    private val eventProducer: BookProducer,
    validators: List<ValidationStrategy<CreateBookCommand>>
) : BaseUseCase<CreateBookCommand>(validators) {
    override fun handle(command: CreateBookCommand): IResponse {
        val validationResult = this.runValidations(command)

        if (validationResult.failed) {
            return returnValidationErrors(validationResult)
        }

        val book = command.request.toBookModel();
        bookRepository.save(book);

        val event = book.toEvent();
        eventProducer.sendEvent(event);

        val response = book.toResponse();
        return CreatedResponse(response);
    }
}