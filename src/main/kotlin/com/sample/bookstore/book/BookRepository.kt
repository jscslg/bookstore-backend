package com.sample.bookstore.book

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Book,Long> {
    fun findAllByOrderByNameAsc(): List<Book>

    @Query(value = "select * from books where name = :name", nativeQuery = true)
    fun findAllWithName(@Param("name") name: String): List<Book>
}