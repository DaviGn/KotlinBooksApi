package com.mercadolivro.data.repository

import com.mercadolivro.domain.entities.BookModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<BookModel, Int> {

    override fun findAll(pageable: Pageable): Page<BookModel>
}