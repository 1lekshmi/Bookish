package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.SearchBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchBookService extends DatabaseService {
    public List<SearchBook> search(SearchBook book) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT name,author,genre FROM Books WHERE name = :name ORDER BY author,name")
                        .bind("name", book.getName())
                        .mapToBean(SearchBook.class)
                        .list()
        );
    }
}
