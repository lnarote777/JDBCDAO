package org.example.dao

import org.example.BookEntity
import org.example.output.IOuputInfo
import java.sql.SQLException
import javax.sql.DataSource

class BookDAO(private val dataSource: DataSource, private val console: IOuputInfo): IBookDAO {
    override fun insert(book: BookEntity): BookEntity? {
        val sql = "INSERT INTO libros (id, title, author) VALUES (?, ?, ?)"

        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, book.id)
                    stmt.setString(2, book.title)
                    stmt.setString(3, book.author)
                    val rs = stmt.executeUpdate()
                    if (rs == 1){
                        book
                    }else{
                        console.showMessage("Error - Insert query failed! ($rs records inserted)")
                        null
                    }
                }
            }
        }catch (e: SQLException){
            console.showMessage("Error - Insert query failed! (${e.message})")
            null
        }
    }

    override fun update(book: BookEntity): BookEntity? {
        val sql = "UPDATE libros SET id = ?, title = ?, author = ? WHERE id = ?"

        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, book.id)
                    stmt.setString(2, book.title)
                    stmt.setString(3, book.author)
                    val rs = stmt.executeUpdate()

                    if (rs == 1){
                        book
                    }else{
                        console.showMessage("Error - Update query failed! ($rs records inserted)")
                        null
                    }
                }
            }
        }catch (e: SQLException){
            console.showMessage("Error - Insert query failed! (${e.message})")
            null
        }
    }

    override fun deleteById(id: Int): Boolean? {
        val sql = "DELETE FROM libros WHERE id = ?"

        return try{
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
                    (stmt.executeUpdate() == 1)
                }
            }
        }catch (e: SQLException){
            console.showMessage("Error - Insert query failed! (${e.message})")
            null
        }
    }

    override fun selectById(id: Int): BookEntity? {
        val sql = "SELECT * FROM libros WHERE id = ?"

        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        BookEntity(
                            id = rs.getInt("id"),
                            title = rs.getString("title"),
                            author = rs.getString("author")
                        )
                    } else {
                        null
                    }
                }
            }
        }catch (e: SQLException){
            console.showMessage("Error - Insert query failed! (${e.message})")
            null
        }
    }

    override fun selectAll(): List<BookEntity>? {
        val sql = "SELECT * FROM libros"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val books = mutableListOf<BookEntity>()
                    while (rs.next()) {
                        books.add(
                            BookEntity(
                                id = rs.getInt("id"),
                                title = rs.getString("title"),
                                author = rs.getString("author")
                            )
                        )
                    }
                    books
                }
            }
        }catch (e: SQLException){
            console.showMessage("Error - Insert query failed! (${e.message})")
            null
        }
    }
}
