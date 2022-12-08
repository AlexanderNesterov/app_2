package com.example.app_2.repositories

import com.example.app_2.models.Book

class BookRepository(_bookList: ArrayList<Book>) {
    private val invalidBook = Book(-1, "", "", -1, -1)
    private val bookList: ArrayList<Book> = _bookList

    fun getInvalidBook(): Book {
        return invalidBook
    }

    fun getById(id: Int): Book {
        for (i in bookList.indices) {
            if (bookList[i].id == id) {
                return bookList[i]
            }
        }
        return invalidBook
    }

    fun getAll(): ArrayList<Book> {
        return bookList
    }

    fun createBook(book: Book): Int {
        if (book.name.isNotEmpty() && book.author.isNotEmpty() && book.year > 0 && book.pages > 0) {
            var maxId = 0
            for (i in bookList.indices) {
                if (bookList[i].id > maxId) {
                    maxId = bookList[i].id
                }
            }
            book.id = maxId++
            bookList.add(book)
            return book.id
        }
        return invalidBook.id
    }

    fun updateBook(bookToUpdate: Book): Book {
        val existingBook = getById(bookToUpdate.id)
        val isBookValid = isBookValid(bookToUpdate)

        return if (!isBookValid) {
            invalidBook
        } else {
            existingBook.setInfoFromBook(bookToUpdate)
            existingBook
        }
    }

    fun deleteById(id: Int): Int {
        val foundBook = getById(id)
        if (foundBook == invalidBook) {
            return invalidBook.id
        }

        bookList.remove(foundBook)
        return foundBook.id
    }

    private fun isBookValid(book: Book): Boolean {
        return book.name.isNotEmpty()
                && book.author.isNotEmpty()
                && book.year > 0
                && book.pages > 0
    }
}