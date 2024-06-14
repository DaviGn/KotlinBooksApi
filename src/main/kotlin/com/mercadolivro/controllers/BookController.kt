package com.mercadolivro.controllers

import com.mercadolivro.application.useCases.books.create.CreateBookCommand
import com.mercadolivro.application.useCases.books.delete.DeleteBookCommand
import com.mercadolivro.application.useCases.books.get.GetBookQuery
import com.mercadolivro.application.useCases.books.list.ListBooksQuery
import com.mercadolivro.application.useCases.books.update.UpdateBookCommand
import com.mercadolivro.domain.requests.CreateBookRequest
import com.mercadolivro.domain.requests.UpdateBookRequest
import com.mercadolivro.domain.responses.BookResponse
import com.trendyol.kediatr.Mediator
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("books")
class BookController(
    private val mediator: Mediator
) {
    @GetMapping
    suspend fun list(@PageableDefault(page = 0, size = 10) pageable: Pageable): ResponseEntity<Page<BookResponse>> {
        val useCase = ListBooksQuery(pageable);
        val result = mediator.send(useCase);
        return result.getResponse()
    }

    @GetMapping("/{id}")
    suspend fun get(@PathVariable id: Int): ResponseEntity<BookResponse> {
        val useCase = GetBookQuery(id);
        val result = mediator.send(useCase);
        return result.getResponse();
    }

    @PostMapping
    suspend fun post(@RequestBody @Valid request: CreateBookRequest): ResponseEntity<BookResponse> {
        val useCase = CreateBookCommand(request);
        val result = mediator.send(useCase);
        return result.getResponse();
    }

    @PutMapping("/{id}")
    suspend fun put(@PathVariable id: Int, @RequestBody @Valid request: UpdateBookRequest): ResponseEntity<BookResponse> {
        val useCase = UpdateBookCommand(id, request);
        val result = mediator.send(useCase);
        return result.getResponse();
    }

    @DeleteMapping("/{id}")
    suspend fun delete(@PathVariable id: Int): ResponseEntity<Unit> {
        val useCase = DeleteBookCommand(id);
        val result = mediator.send(useCase);
        return result.getResponse();
    }
}