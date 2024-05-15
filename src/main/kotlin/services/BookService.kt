package org.example.services

import org.example.BookEntity
import org.example.dao.IBookDAO

class BookService(private val bookDAO: IBookDAO): IBookService {
    override fun insert(book: BookEntity): BookEntity? {
        return bookDAO.insert(book)
    }

    override fun update(book: BookEntity): BookEntity? {
        return bookDAO.update(book)
    }

    override fun deleteById(id: Int): Boolean? {
        return bookDAO.deleteById(id)
    }

    override fun selectById(id: Int): BookEntity? {
        return bookDAO.selectById(id)
    }

    override fun selectAll(): List<BookEntity>? {
        return bookDAO.selectAll()
    }
}