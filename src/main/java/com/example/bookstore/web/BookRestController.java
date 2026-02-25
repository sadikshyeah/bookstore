package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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

    // REST service to update an existing book
    @RequestMapping(value = "/api/books/{id}", method = RequestMethod.PUT)
    public @ResponseBody Book updateBook(@PathVariable("id") Long bookId, @RequestBody Book updatedBook, Model model) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            return bookRepository.save(existingBook);
        } else {
            throw new RuntimeException("Book not found");
        }
    }

    @RequestMapping(value = "/api/books/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteBook(@PathVariable("id") Long bookId, Model model) {
        bookRepository.deleteById(bookId);
        return "ok";
    }
}