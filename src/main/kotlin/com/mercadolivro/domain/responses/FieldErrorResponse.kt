package com.mercadolivro.domain.responses

data class FieldErrorResponse(
    var messsage: String,
    var fieldName: String?
)
