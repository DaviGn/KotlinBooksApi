package com.mercadolivro.domain.responses.bases

import org.springframework.http.ResponseEntity

open class OkResponse() : IResponse {
    override fun getResponse(): ResponseEntity<Any> {
        return ResponseEntity.ok().build();
    }
}

class OkObjectResponse<T>(
    private val result: T
) : OkResponse(), IResponse {
    override fun getResponse(): ResponseEntity<Any> {
        return ResponseEntity.ok(result);
    }
}

class CreatedResponse<T>(
    private val result: T
) : IResponse {
    override fun getResponse(): ResponseEntity<Any> {
        return ResponseEntity.status(201).body(result);
    }
}

class NoContentResponse() : IResponse {
    override fun getResponse(): ResponseEntity<Any> {
        return ResponseEntity.noContent().build();
    }
}