package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Book;

import java.util.List;

public class BookPageModel {
    private List<Book> book;

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
}
