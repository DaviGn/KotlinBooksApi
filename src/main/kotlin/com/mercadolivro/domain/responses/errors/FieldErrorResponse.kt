package com.mercadolivro.domain.responses.errors

import com.mercadolivro.domain.interfaces.IErrorResponse

data class FieldErrorResponse(
    var messsage: String,
    var fieldName: String?
)

class FieldError(
    private var messsage: String,
    private var fieldName: String?
): IErrorResponse {
    override fun getResponse(): FieldErrorResponse {
        return FieldErrorResponse(messsage, fieldName)
    }
}