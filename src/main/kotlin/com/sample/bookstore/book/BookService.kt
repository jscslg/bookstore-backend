package com.sample.bookstore.book

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookService @Autowired constructor(val bookRepository: BookRepository) {
    fun fetchAll(): List<Book> {
        return bookRepository.findAllByOrderByNameAsc()
    }

    fun fetchByName(name: String): List<Book> {
        return bookRepository.findAllWithName(name)
    }

    fun addBook(bookRequest: BookRequest): Book {
        val book = bookRequest.toBook()
        bookRepository.save(book)
        return book
    }

    fun updateBook(id: Long, bookRequest: BookRequest): Book {
        bookRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        val book = bookRequest.toBook(id)
        bookRepository.save(book)
        return book;
    }

    fun deleteBook(id: Long) {
        bookRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        bookRepository.deleteById(id)
    }
}