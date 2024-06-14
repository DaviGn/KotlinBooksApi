package com.mercadolivro.application.useCases.books.create

import com.mercadolivro.application.kafka.producers.BookProducer
import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.interfaces.ValidationStrategy
import com.mercadolivro.domain.mappers.toBookModel
import com.mercadolivro.domain.mappers.toEvent
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.bases.BadRequestResponse
import com.mercadolivro.domain.responses.bases.CreatedResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.mercadolivro.domain.utils.validate
import com.trendyol.kediatr.CommandWithResultHandler
import org.springframework.stereotype.Component

@Component
class CreateBookCommandHandler(
    private val bookRepository: BookRepository,
    private val eventProducer: BookProducer,
    private val validators: List<ValidationStrategy<CreateBookCommand>>
) : CommandWithResultHandler<CreateBookCommand, IResponse> {
    override suspend fun handle(command: CreateBookCommand): IResponse {
        val validationResult = validators.validate(command)

        if (validationResult.failed) {
            return BadRequestResponse(validationResult.errors)
        }

        val book = command.request.toBookModel();
        bookRepository.save(book);

        val event = book.toEvent();
        eventProducer.sendEvent(event);

        val response = book.toResponse();
        return CreatedResponse(response);
    }
}

//abstract  class BaseUseCase<TCommand>(
//    protected val validators: List<ValidationStrategy<TCommand>>
//) {
//    protected fun validate(command: TCommand) : ValidationResult {
//        val validationResult = validators.validate(command)
//        return validationResult;
//    }
//}
//
//class CreateBookUseCase (
//    private val bookRepository: BookRepository,
//    private val eventProducer: BookProducer,
//    val validators: List<ValidationStrategy<CreateBookCommand>>
//) : BaseUseCase<CreateBookCommand>(validators) {
//    fun handle(command: CreateBookCommand): IResponse {
//        val validationResult = this.validate(command)
//
//        if (validationResult.failed) {
//            return BadRequestResponse(validationResult.errors)
//        }
//
//        val book = command.request.toBookModel();
//        bookRepository.save(book);
//
//        val event = book.toEvent();
//        eventProducer.sendEvent(event);
//
//        val response = book.toResponse();
//        return CreatedResponse(response);
//    }
//}


