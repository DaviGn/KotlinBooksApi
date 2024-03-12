package com.mercadolivro.domain.exceptions

class BadRequestException(override val message: String, val errorCode: String) : Exception() {
}