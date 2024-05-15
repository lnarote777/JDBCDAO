package org.example.dao

import org.example.BookEntity

interface IBookDAO {
    fun insert(book: BookEntity): BookEntity?
    fun update(book: BookEntity): BookEntity?
    fun deleteById(id: Int): Boolean?
    fun selectById(id: Int): BookEntity?
    fun selectAll(): List<BookEntity>?

}