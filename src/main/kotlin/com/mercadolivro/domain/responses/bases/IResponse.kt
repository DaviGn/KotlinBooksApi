package com.mercadolivro.domain.responses.bases

import org.springframework.http.ResponseEntity

interface IResponse<T> {
    fun getResponse() : ResponseEntity<T>
}