package com.mercadolivro.domain.entities

import java.math.BigDecimal
import jakarta.persistence.*

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