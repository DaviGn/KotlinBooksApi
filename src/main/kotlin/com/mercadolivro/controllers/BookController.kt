package com.mercadolivro.controllers

import com.mercadolivro.application.useCases.books.*
import com.mercadolivro.domain.requests.CreateBookRequest
import com.mercadolivro.domain.requests.UpdateBookRequest
import com.mercadolivro.domain.responses.BookResponse
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("books")
class BookController(
    private val listBooksUseCaseHandler: ListBooksUseCaseHandler,
    private val getBookUseCaseHandler: GetBookUseCaseHandler,
    private val createBookHandlerHandler: CreateBookUseCaseHandler,
    private val updateBookUseCaseHandler: UpdateBookUseCaseHandler,
    private val deleteBookUseCaseHandler: DeleteBookUseCaseHandler
) {
    @GetMapping
    fun list(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
        val useCase = ListBooksUseCase(pageable);
        return listBooksUseCaseHandler.handle(useCase);
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): BookResponse {
        val useCase = GetBookUseCase(id);
        return getBookUseCaseHandler.handle(useCase);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@RequestBody @Valid request: CreateBookRequest): BookResponse {
        val useCase = CreateBookUseCase(request);
        return createBookHandlerHandler.handle(useCase);
    }

    @PutMapping("/{id}")
    fun put(@PathVariable id: Int, @RequestBody @Valid request: UpdateBookRequest): BookResponse {
        val useCase = UpdateBookUseCase(id, request);
        return updateBookUseCaseHandler.handle(useCase);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        val useCase = DeleteBookUseCase(id);
        deleteBookUseCaseHandler.handle(useCase);
    }
}