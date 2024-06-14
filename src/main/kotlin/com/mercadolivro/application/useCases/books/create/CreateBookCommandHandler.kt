package com.mercadolivro.application.useCases.books.create

import com.mercadolivro.application.kafka.producers.BookProducer
import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.mappers.toBookModel
import com.mercadolivro.domain.mappers.toEvent
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.BookResponse
import com.mercadolivro.domain.responses.bases.CreatedResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.trendyol.kediatr.CommandWithResultHandler
import org.springframework.stereotype.Component

@Component
class CreateBookCommandHandler(
    private val bookRepository: BookRepository,
    private val eventProducer: BookProducer
) : CommandWithResultHandler<CreateBookCommand, IResponse<BookResponse>> {
    override suspend fun handle(command: CreateBookCommand): IResponse<BookResponse> {
        val book = command.request.toBookModel();
        bookRepository.save(book);

        val event = book.toEvent();
        eventProducer.sendEvent(event);

        val response = book.toResponse();
        return CreatedResponse(response);
    }
}