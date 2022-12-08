package com.example.app_2.activities

import android.content.Context
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

        deleteButton!!.setOnClickListener {
            deleteBook(book)
        }

        updateButton!!.setOnClickListener {
            try {
                book.name = bookName!!.text.toString()
                book.author = bookAuthor!!.text.toString()
                book.pages = bookPages!!.text.toString().toInt()
                book.year = bookYear!!.text.toString().toInt()
                updateBook(book)
            } catch (e: java.lang.NumberFormatException) {
                Toast.makeText(
                    this@InfoActivity,
                    Constants.FAILED_UPDATE,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun deleteBook(book: Book) {
        val deletedBookId = bookController.deleteById(book.id)
        if (deletedBookId != -1) {
            Toast.makeText(
                this@InfoActivity,
                Constants.SUCCESS_DELETE,
                Toast.LENGTH_LONG
            ).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(
                this@InfoActivity,
                Constants.FAILED_DELETE,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    private fun updateBook(book: Book) {
        val updatedBook = bookController.updateBook(book)

        if (updatedBook.id != -1) {
            Toast.makeText(
                this@InfoActivity,
                Constants.SUCCESS_UPDATE,
                Toast.LENGTH_LONG
            )
                .show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(
                this@InfoActivity,
                Constants.FAILED_UPDATE,
                Toast.LENGTH_LONG
            )
                .show()
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