package com.mercadolivro.domain.responses.errors

import com.mercadolivro.domain.interfaces.IErrorResponse

data class ErrorResponse(
    var message: String
)

class Error(
    private var messsage: String
): IErrorResponse {
    override fun getResponse(): ErrorResponse {
        return ErrorResponse(messsage)
    }
}