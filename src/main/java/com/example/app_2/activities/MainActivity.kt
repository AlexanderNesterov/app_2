package com.example.app_2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.app_2.R
import com.example.app_2.controllers.BookController
import com.example.app_2.models.Book

class MainActivity : AppCompatActivity() {
    private val bookController: BookController = BookController()

    private var listView: ListView? = null
    private var createButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.bookListView)
        createButton = findViewById(R.id.createButton)

        val books = bookController.getAll()

        val context = this
        listView!!.setOnItemClickListener { _, _, position, _ ->
            val selectedBook = books[position]
            val detailIntent = InfoActivity.newIntent(context, selectedBook.id)
            startActivity(detailIntent)
        }

        val bookInfoList = arrayOfNulls<String>(books.size)
        for (i in 0 until books.size) {
            val book = books[i]
            bookInfoList[i] = book.name + " " + book.author
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bookInfoList)
        listView!!.adapter = adapter

        createButton!!.setOnClickListener{
            val intent = Intent(this, NewBookActivity::class.java)
            startActivity(intent)
        }
    }
}