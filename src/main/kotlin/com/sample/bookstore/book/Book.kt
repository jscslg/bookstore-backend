package com.sample.bookstore.book

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "books")
data class Book(
    val name: String,
    val authorName: String,
    @Embedded
    val price: Money,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null
) {
    fun toResponse() = BookResponse(id,name,authorName,price)
}
