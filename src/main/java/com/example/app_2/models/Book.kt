package com.example.app_2.models

class Book(_id: Int, _name: String, _author: String, _year: Int, _pages: Int) {
    var id: Int = _id
    var name: String = _name
    var author: String = _author
    var year: Int = _year
    var pages: Int = _pages

    fun setInfoFromBook(book: Book) {
        id = book.id
        name = book.name
        year = book.year
        pages = book.pages
    }

    override fun toString(): String {
        return "Book(id=$id, name='$name', author='$author', year=$year, pages=$pages)"
    }
}