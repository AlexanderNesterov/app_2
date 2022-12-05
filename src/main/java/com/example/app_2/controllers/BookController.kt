package com.example.app_2.controllers

import com.example.app_2.models.Book
import com.example.app_2.repositories.BookRepository

class BookController {
    private val bookRepository: BookRepository = BookRepository(getBookList())

    fun getById(id: Int): Book {
        return bookRepository.getById(id)
    }

    fun getAll(): ArrayList<Book> {
        return bookRepository.getAll()
    }

    fun createBook(book: Book): Int {
        return bookRepository.createBook(book)
    }

    fun updateBook(book: Book): Book {
        return bookRepository.updateBook(book)
    }

    fun deleteById(id: Int): Int {
        return bookRepository.deleteById(id)
    }

    companion object {
        private val BOOK_LIST = arrayListOf(
            Book(1, "BooK_1", "Author_1", 1901, 100),
            Book(2, "BooK_2", "Author_2", 1902, 200),
            Book(3, "BooK_3", "Author_3",1903, 300),
            Book(4, "BooK_4", "Author_4", 1904, 400),
            Book(5, "BooK_5", "Author_5", 1905, 500)
        )

        fun getBookList(): ArrayList<Book> {
            return BOOK_LIST
        }
    }
}