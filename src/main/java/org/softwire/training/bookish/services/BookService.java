package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends DatabaseService{

    public List<Book> getAllBooks(){
        return jdbi.withHandle(handle ->
            handle.createQuery("SELECT name,author,genre FROM Books ORDER BY author,name")
                    .mapToBean(Book.class)
                    .list()
        );
    }

    public void addBooks(Book book){
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO Books (name, author, isbn, genre) VALUES (:name, :author, :isbn, :genre)")
                        .bind("name", book.getName())
                        .bind("author", book.getAuthor())
                        .bind("isbn", book.getIsbn())
                        .bind("genre",book.getGenre())
                        .execute()
        );
    }

    public void addCopy(Book book){
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE Books SET numberOfCopiesAvailable = numberOfCopiesAvailable+1, totalNumberOfCopies = totalNumberOfCopies+1 WHERE name = :name AND author = :author")
                        .bind("name", book.getName())
                        .bind("author", book.getAuthor())
                        .execute()
        );
    }

    public void deleteCopy(Book book){
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE Books SET numberOfCopiesAvailable = numberOfCopiesAvailable-1, totalNumberOfCopies = totalNumberOfCopies-1 WHERE name = :name AND author = :author")
                        .bind("name", book.getName())
                        .bind("author", book.getAuthor())
                        .execute()
        );
    }

//    public List<Book> search(Book book){
//        return jdbi.withHandle(handle ->
//                handle.createQuery("SELECT name,author,genre FROM Books WHERE name LIKE :name")
//                        .bind("name", book.getName())
//                        .mapToBean(Book.class)
//                        .list()
//                );
//         jdbi.useHandle(handle ->
//                handle.createUpdate("SELECT name,author,genre FROM Books WHERE name LIKE %:name%")
//                        .bind("name",book.getName())
//                        .execute()
//                );
//    }

}
