package com.mercadolivro.domain.responses.bases

import org.springframework.http.ResponseEntity

open class OkResponse<T>() : IResponse<T> {
    override fun getResponse(): ResponseEntity<T> {
        return ResponseEntity.ok().build();
    }
}

class OkObjectResponse<T>(
    private val result: T
) : OkResponse<T>(), IResponse<T> {
    override fun getResponse(): ResponseEntity<T> {
        return ResponseEntity.ok(result);
    }
}

class CreatedResponse<T>(
    private val result: T
) : IResponse<T> {
    override fun getResponse(): ResponseEntity<T> {
        return ResponseEntity.status(201).body(result);
    }
}

class NoContentResponse() : IResponse<Unit> {
    override fun getResponse(): ResponseEntity<Unit> {
        return ResponseEntity.noContent().build();
    }
}