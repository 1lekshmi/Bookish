package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.SearchBook;

import java.util.List;

public class SearchBookPageModel {
    private List<SearchBook> books;

    public SearchBookPageModel() {
    }

    public List<SearchBook> getBook() {
        return books;
    }

    public void setBook(List<SearchBook> searchBooks) {
        this.books = searchBooks;
    }
}
