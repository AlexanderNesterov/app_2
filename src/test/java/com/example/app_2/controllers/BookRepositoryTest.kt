package com.example.app_2.controllers

import com.example.app_2.models.Book
import com.example.app_2.repositories.BookRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class BookRepositoryTest {
    private val bookList: ArrayList<Book> = arrayListOf(
        Book(1, "BooK_1", "Author_1", 1901, 100),
        Book(2, "BooK_2", "Author_2", 1902, 200),
        Book(3, "BooK_3", "Author_3",1903, 300)
    )
    private var bookRepository: BookRepository = BookRepository(bookList)

    @Before
    fun init() {
        bookRepository = BookRepository(bookList)
    }

    @Test
    fun getById_ValidId_SuccessfulReturn() {
        val id = 1
        val expectedBook = getBookById(id)
        val foundBook = bookRepository.getById(id)

        assertEquals(expectedBook?.id, foundBook.id)
        assertEquals(expectedBook?.name, foundBook.name)
        assertEquals(expectedBook?.author, foundBook.author)
        assertEquals(expectedBook?.year, foundBook.year)
        assertEquals(expectedBook?.pages, foundBook.pages)
    }

    @Test
    fun getById_InvalidId_FailedReturn() {
        val id = 100
        val expectedBook = bookRepository.getInvalidBook()
        val foundBook = bookRepository.getById(id)

        assertEquals(expectedBook, foundBook)
    }

    @Test
    fun getAll_BooksExist_SuccessfulReturn() {
        val foundBookList = bookRepository.getAll()

        assertEquals(bookList, foundBookList)
    }

    @Test
    fun getAll_BooksDoesNotExist_FailedReturn() {
        val expectedBookList: ArrayList<Book> = arrayListOf()
        bookRepository = BookRepository(expectedBookList)

        val foundBookList = bookRepository.getAll()

        assertEquals(expectedBookList, foundBookList)
    }

    @Test
    fun createBook_ValidParameters_SuccessfulCreation() {
        val bookToCreate = Book(1, "Book_1", "Author_1", 1, 1)
        val createdIdBook = bookRepository.createBook(bookToCreate)

        assertEquals(bookToCreate.id, createdIdBook)
    }

    @Test
    fun createBook_InvalidParameters_FailedCreation() {
        val bookToCreate = Book(1, "Book_1", "Author_1", -1, -100)
        val expectedId = bookRepository.getInvalidBook().id
        val createdIdBook = bookRepository.createBook(bookToCreate)

        assertEquals(expectedId, createdIdBook)
    }

    @Test
    fun updateBook_ValidParameters_SuccessFulUpdate() {
        val bookToUpdate = Book(1, "Book_2", "Author_1", 2, 1)
        val expectedBook = getBookById(bookToUpdate.id)
        val updatedBook = bookRepository.updateBook(bookToUpdate)

        assertEquals(expectedBook, updatedBook)
    }

    @Test
    fun updateBook_InvalidParameters_FailedUpdate() {
        val bookToUpdate = Book(100, "Book_2", "Author_1", 2, 1)
        val expectedBook = bookRepository.getInvalidBook()
        val updatedBook = bookRepository.updateBook(bookToUpdate)

        assertEquals(expectedBook, updatedBook)
    }

    @Test
    fun deleteById_ValidId_SuccessfulDelete() {
        val bookIdToDelete = 2
        val deletedId = bookRepository.deleteById(bookIdToDelete)

        assertEquals(bookIdToDelete, deletedId)
    }

    @Test
    fun deleteById_InvalidId_FailedDelete() {
        val bookIdToDelete = 100
        val expectedId = bookRepository.getInvalidBook().id
        val deletedId = bookRepository.deleteById(bookIdToDelete)

        assertEquals(expectedId, deletedId)
    }

    private fun getBookById(id: Int): Book? {
        for (i in bookList.indices) {
            if (id == bookList[i].id) {
                return bookList[i]
            }
        }
        return null
    }
}