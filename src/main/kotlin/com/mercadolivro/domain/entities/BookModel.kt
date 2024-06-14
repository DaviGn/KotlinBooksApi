package com.mercadolivro.domain.entities

import jakarta.persistence.*
import java.math.BigDecimal

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