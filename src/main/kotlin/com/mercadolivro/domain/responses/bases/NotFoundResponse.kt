package com.mercadolivro.domain.responses.bases

import org.springframework.http.ResponseEntity

class NotFoundResponse : IResponse {
    override fun getResponse(): ResponseEntity<Any> {
        return ResponseEntity.notFound().build();
    }
}
