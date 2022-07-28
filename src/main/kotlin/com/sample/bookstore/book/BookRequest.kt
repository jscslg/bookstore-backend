package com.sample.bookstore.book

import javax.persistence.Embedded

data class BookRequest (
    val name:String,
    val authorName:String,
    @Embedded
    val price: Money
) {
    fun toBook(id:Long?=null): Book {
        return Book(name,authorName,price,id)
    }
}