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
import org.springframework.http.ResponseEntity
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
    fun list(@PageableDefault(page = 0, size = 10) pageable: Pageable): ResponseEntity<Any>  {
        val useCase = ListBooksUseCase(pageable);
        val result = listBooksUseCaseHandler.handle(useCase);
        return result.getResponse()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int) : ResponseEntity<Any> {
        val useCase = GetBookUseCase(id);
        val result = getBookUseCaseHandler.handle(useCase);
        return result.getResponse();
    }

    @PostMapping
    fun post(@RequestBody @Valid request: CreateBookRequest): ResponseEntity<Any> {
        val useCase = CreateBookUseCase(request);
        val result = createBookHandlerHandler.handle(useCase);
        return  result.getResponse();
    }

    @PutMapping("/{id}")
    fun put(@PathVariable id: Int, @RequestBody @Valid request: UpdateBookRequest): ResponseEntity<Any> {
        val useCase = UpdateBookUseCase(id, request);
        val result =  updateBookUseCaseHandler.handle(useCase);
        return  result.getResponse();
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) : ResponseEntity<Any>{
        val useCase = DeleteBookUseCase(id);
        val result =  deleteBookUseCaseHandler.handle(useCase);
        return  result.getResponse();
    }
}