package com.mercadolivro.controllers

import com.mercadolivro.application.useCases.books.create.CreateBookCommand
import com.mercadolivro.application.useCases.books.create.CreateBookCommandUseCase
import com.mercadolivro.application.useCases.books.delete.DeleteBookCommand
import com.mercadolivro.application.useCases.books.delete.DeleteBookCommandUseCase
import com.mercadolivro.application.useCases.books.get.GetBookQuery
import com.mercadolivro.application.useCases.books.get.GetBookQueryUseCase
import com.mercadolivro.application.useCases.books.list.ListBooksQuery
import com.mercadolivro.application.useCases.books.list.ListBooksQueryUseCase
import com.mercadolivro.application.useCases.books.update.UpdateBookCommand
import com.mercadolivro.application.useCases.books.update.UpdateBookCommandUseCase
import com.mercadolivro.domain.requests.CreateBookRequest
import com.mercadolivro.domain.requests.UpdateBookRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("books")
class BookController(
    private val createBookCommandUseCase: CreateBookCommandUseCase,
    private val deleteBookCommandUseCase: DeleteBookCommandUseCase,
    private val getBookQueryUseCase: GetBookQueryUseCase,
    private val listBooksQueryUseCase: ListBooksQueryUseCase,
    private val updateBookCommandUseCase: UpdateBookCommandUseCase
) {
    @GetMapping
    fun list(@PageableDefault(page = 0, size = 10) pageable: Pageable): ResponseEntity<Any> {
        val useCase = ListBooksQuery(pageable);
        val result = listBooksQueryUseCase.execute(useCase);
        return result.getResponse()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<Any> {
        val useCase = GetBookQuery(id);
        val result = getBookQueryUseCase.execute(useCase);
        return result.getResponse();
    }

    @PostMapping
    fun post(@RequestBody request: CreateBookRequest): ResponseEntity<Any> {
        val command = CreateBookCommand(request);
        val result = createBookCommandUseCase.execute(command);
        return result.getResponse();
    }

    @PutMapping("/{id}")
    fun put(@PathVariable id: Int, @RequestBody request: UpdateBookRequest): ResponseEntity<Any> {
        val command = UpdateBookCommand(id, request);
        val result = updateBookCommandUseCase.execute(command);
        return result.getResponse();
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        val useCase = DeleteBookCommand(id);
        val result = deleteBookCommandUseCase.execute(useCase);
        return result.getResponse();
    }
}