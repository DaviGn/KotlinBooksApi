package com.mercadolivro.domain.responses.bases

import org.springframework.http.ResponseEntity

class BadRequestResponse<T>(
    private val result: T
) : IResponse {
    override fun getResponse(): ResponseEntity<Any> {
        return ResponseEntity.status(400).body(result);
    }
}