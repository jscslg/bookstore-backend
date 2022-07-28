package com.sample.bookstore.book

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController @Autowired constructor(val bookService: BookService) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun list(): List<BookResponse> {
        val books: List<Book> = bookService.fetchAll()
        return books.map { book: Book -> book.toResponse() }
    }

    @GetMapping(params = ["name"])
    @ResponseStatus(HttpStatus.OK)
    fun listByName(@RequestParam name: String): List<BookResponse> {
        val books: List<Book> = bookService.fetchByName(name)
        return books.map { book: Book -> book.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBook(@RequestBody bookRequest: BookRequest): Book {
        return bookService.addBook(bookRequest)
    }

    @PutMapping("/{id}")
    fun updateBook(@PathVariable("id") id:Long,@RequestBody bookRequest: BookRequest): ResponseEntity<Book> = try {
        val book = bookService.updateBook(id, bookRequest)
        ResponseEntity<Book>(book,HttpStatus.OK)
    } catch (_ : ResourceNotFoundException){
        ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable("id") id:Long): ResponseEntity<Unit> =try {
        bookService.deleteBook(id)
        ResponseEntity(HttpStatus.NO_CONTENT)
    } catch (_ : ResourceNotFoundException){
        ResponseEntity(HttpStatus.NOT_FOUND)
    }
}