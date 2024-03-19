package com.mercadolivro.application.useCases.books

import com.mercadolivro.application.kafka.producers.BookProducer
import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.mappers.toBookModel
import com.mercadolivro.domain.mappers.toEvent
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.requests.CreateBookRequest
import com.mercadolivro.domain.responses.BookResponse
import com.mercadolivro.domain.responses.bases.CreatedResponse
import com.mercadolivro.domain.responses.bases.IResponse
import org.springframework.stereotype.Service

data class CreateBookUseCase(
    val request: CreateBookRequest
)

@Service
class CreateBookUseCaseHandler(
    private val bookRepository: BookRepository,
    private val eventProducer: BookProducer
) {
    fun handle(data: CreateBookUseCase): IResponse<BookResponse> {
        val book = data.request.toBookModel();
        bookRepository.save(book);

        val event = book.toEvent();
        eventProducer.sendEvent(event);

        val response = book.toResponse();
        return CreatedResponse(response);
    }
}