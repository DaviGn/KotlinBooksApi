package com.mercadolivro.domain.responses.bases

import org.springframework.http.ResponseEntity

class NotFoundResponse<T> : IResponse<T> {
    override fun getResponse(): ResponseEntity<T> {
        return ResponseEntity.notFound().build();
    }
}
