package com.mercadolivro.domain.entities

import com.mercadolivro.domain.exceptions.BadRequestException
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "Books")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal

)