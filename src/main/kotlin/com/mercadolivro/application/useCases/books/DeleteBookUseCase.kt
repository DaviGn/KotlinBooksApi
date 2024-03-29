package com.mercadolivro.application.useCases.books

import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.responses.bases.IResponse
import com.mercadolivro.domain.responses.bases.NoContentResponse
import org.springframework.stereotype.Service

data class DeleteBookUseCase(
    val id: Int
)

@Service
class DeleteBookUseCaseHandler(
    private val bookRepository: BookRepository
) {
    fun handle(data: DeleteBookUseCase): IResponse<Unit> {
        bookRepository.deleteById(data.id)
        return NoContentResponse();
    }
}