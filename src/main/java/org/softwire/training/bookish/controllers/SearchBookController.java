package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.SearchBook;
import org.softwire.training.bookish.models.page.SearchBookPageModel;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.SearchBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchBookController {
    private final SearchBookService searchBookService;

    @Autowired
    public SearchBookController(SearchBookService searchBookService){
        this.searchBookService = searchBookService;
    }

    @RequestMapping("")
    ModelAndView search(@ModelAttribute SearchBook book){
        List<SearchBook> searchedBooks = searchBookService.search(book);

        SearchBookPageModel searchBookPageModel = new SearchBookPageModel();
        searchBookPageModel.setBook(searchedBooks);

        return new ModelAndView("search", "model", searchBookPageModel);
    }


}
