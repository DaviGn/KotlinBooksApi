package com.mercadolivro.domain.responses.bases

import org.springframework.http.ResponseEntity

interface IResponse {
    fun getResponse() : ResponseEntity<Any>
}