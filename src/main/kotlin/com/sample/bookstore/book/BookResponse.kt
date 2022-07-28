package com.sample.bookstore.book

import javax.persistence.Embedded

data class BookResponse(
    val id: Long?=null,
    val name: String,
    val authorName: String,
    @Embedded
    val price: Money
)
