package com.mercadolivro.application.useCases.books.create

import com.mercadolivro.application.kafka.producers.BookProducer
import com.mercadolivro.application.useCases.books.BaseUseCase
import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.interfaces.IValidationStrategy
import com.mercadolivro.domain.mappers.toBookModel
import com.mercadolivro.domain.mappers.toEvent
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.BookResponse
import com.mercadolivro.domain.responses.bases.IResponse
import org.springframework.stereotype.Component

@Component
class CreateBookCommandUseCase(
    private val bookRepository: BookRepository,
    private val eventProducer: BookProducer,
    validators: List<IValidationStrategy<CreateBookCommand>>
) : BaseUseCase<CreateBookCommand, BookResponse>(validators) {
    override fun handle(request: CreateBookCommand): IResponse {
        val book = request.request.toBookModel();
        bookRepository.save(book);

        val event = book.toEvent();
        eventProducer.sendEvent(event);

        val response = book.toResponse();
        return created(response);
    }
}