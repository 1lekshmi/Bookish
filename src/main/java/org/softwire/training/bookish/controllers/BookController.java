package org.softwire.training.bookish.controllers;


import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.BookPageModel;
import org.softwire.training.bookish.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @RequestMapping("")
    ModelAndView book(){
        List<Book> allBooks = bookService.getAllBooks();

        BookPageModel bookPageModel = new BookPageModel();
        bookPageModel.setBook(allBooks);

        return new ModelAndView("books", "model", bookPageModel);
    }

    @RequestMapping("/add-new-book")
    RedirectView addNewBook(@ModelAttribute Book book){

        bookService.addBooks(book);

        return new RedirectView("/books");
    }

    @RequestMapping("/add-book-copy")
    RedirectView addBookCopy(@ModelAttribute Book book){

        bookService.addCopy(book);

        return new RedirectView("/books");
    }

    @RequestMapping("/delete-book-copy")
    RedirectView deleteBookCopy(@ModelAttribute Book book){

        bookService.deleteCopy(book);

        return new RedirectView("/books");
    }

}
