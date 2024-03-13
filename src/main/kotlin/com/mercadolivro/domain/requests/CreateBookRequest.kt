package com.mercadolivro.domain.requests

import java.math.BigDecimal
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class CreateBookRequest (

    @field:NotEmpty(message = "Nome deve ser informado")
    var name: String,

    @field:NotNull(message = "Price deve ser informado")
    var price: BigDecimal,
)