package com.mercadolivro.domain.requests

import java.math.BigDecimal

data class UpdateBookRequest(
    var name: String?,
    var price: BigDecimal?
)
