package com.mercadolivro.domain.requests

import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CreateBookRequest (

    @field:NotEmpty(message = "Nome deve ser informado")
    var name: String,

    @field:NotNull(message = "Price deve ser informado")
    var price: BigDecimal,
)