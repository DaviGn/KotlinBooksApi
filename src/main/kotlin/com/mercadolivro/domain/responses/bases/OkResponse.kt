package com.mercadolivro.domain.responses.bases

import org.springframework.http.ResponseEntity

open class OkResponse() : IResponse {
    override fun getResponse(): ResponseEntity<Any> {
        return ResponseEntity.ok().build();
    }
}

class OkObjectResponse(
    private val result: Any
) : OkResponse(), IResponse {
    override fun getResponse(): ResponseEntity<Any> {
        return ResponseEntity.ok(result);
    }
}
class NoContentResponse() : IResponse{
    override fun getResponse(): ResponseEntity<Any> {
        return ResponseEntity.noContent().build();
    }
}