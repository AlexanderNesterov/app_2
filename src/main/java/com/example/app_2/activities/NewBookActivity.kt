package com.example.app_2.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_2.R
import com.example.app_2.controllers.BookController
import com.example.app_2.models.Book
import com.example.app_2.models.Constants


class NewBookActivity : AppCompatActivity() {
    private val bookController: BookController = BookController()

    private var createButton: Button? = null

    private var bookName: TextView? = null
    private var bookAuthor: TextView? = null
    private var bookYear: TextView? = null
    private var bookPages: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        createButton = findViewById(R.id.createButton)

        bookName = findViewById(R.id.bookNameField)
        bookAuthor = findViewById(R.id.bookAuthorField)
        bookYear = findViewById(R.id.bookYearField)
        bookPages = findViewById(R.id.bookPagesField)

        createButton!!.setOnClickListener {
            try {
                val book = Book(
                    0,
                    bookName!!.text.toString(),
                    bookAuthor!!.text.toString(),
                    bookPages!!.text.toString().toInt(),
                    bookYear!!.text.toString().toInt()
                )
                createBook(book)
            } catch (e: java.lang.NumberFormatException) {
                Toast.makeText(
                    this@NewBookActivity,
                    Constants.FAILED_CREATE,
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }

    private fun createBook(book: Book) {
        println("book to create: $book")

        val createdBookId = bookController.createBook(book)
        if (createdBookId != -1) {
            Toast.makeText(
                this@NewBookActivity,
                Constants.SUCCESS_CREATE,
                Toast.LENGTH_LONG
            )
                .show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(
                this@NewBookActivity,
                Constants.FAILED_CREATE,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }
}