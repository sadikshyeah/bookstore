package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@Controller
public class BookRestController {

    private final BookRepository bookRepository;

    // Constructor injection
    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // REST service to get all books
    @RequestMapping(value="/api/books", method=RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {
        return (List<Book>) bookRepository.findAll();
    }

    // REST service to get book by id
    @RequestMapping(value="/api/books/{id}", method=RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId);
    }

    // REST service to save new book
    @RequestMapping(value="/api/books", method=RequestMethod.POST)
    public @ResponseBody Book saveBookRest(@RequestBody Book book) {
        return bookRepository.save(book);
    }
}