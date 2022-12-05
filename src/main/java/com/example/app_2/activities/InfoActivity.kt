package com.example.app_2.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app_2.R
import com.example.app_2.controllers.BookController


class InfoActivity : AppCompatActivity() {
    private val bookController: BookController = BookController()

    private var deleteButton: Button? = null
    private var updateButton: Button? = null

    private var bookName: TextView? = null
    private var bookAuthor: TextView? = null
    private var bookYear: TextView? = null
    private var bookPages: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        deleteButton = findViewById(R.id.deleteButton)
        updateButton = findViewById(R.id.updateButton)

        bookName = findViewById(R.id.bookNameField)
        bookAuthor = findViewById(R.id.bookAuthorField)
        bookYear = findViewById(R.id.bookYearField)
        bookPages = findViewById(R.id.bookPagesField)

        val bookId = intent.extras?.getInt(BOOK_ID)
        val book = bookController.getById(bookId!!)

        bookName!!.text = book.name
        bookAuthor!!.text = book.author
        bookYear!!.text = book.year.toString()
        bookPages!!.text = book.pages.toString()

        deleteButton!!.setOnClickListener{
            bookController.deleteById(book.id)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        updateButton!!.setOnClickListener{
            book.name = bookName!!.text.toString()
            book.author = bookAuthor!!.text.toString()
            book.pages = bookPages!!.text.toString().toInt()
            book.year = bookYear!!.text.toString().toInt()

            println("book to update: $book")

            bookController.updateBook(book)
        }
    }

    companion object {
        const val BOOK_ID = "id"

        fun newIntent(context: Context, bookId: Int): Intent {
            val detailIntent = Intent(context, InfoActivity::class.java)

            detailIntent.putExtra(BOOK_ID, bookId)
            return detailIntent
        }
    }
}