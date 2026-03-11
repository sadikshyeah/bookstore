package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@SpringBootTest(classes = BookstoreApplication.class)
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("1984");

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("George Orwell");
    }

    @Test
    public void createNewBook() {
        Category category = new Category("TestCategory");
        categoryRepository.save(category);
        Book book = new Book("Test Book", "Test Author", 2024, "978-0000000000", 9.99, category);
        repository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteBook() {
        List<Book> books = repository.findByTitle("The Hobbit");
        assertThat(books).isNotEmpty();
        Book book = books.get(0);
        repository.delete(book);
        List<Book> remaining = repository.findByTitle("The Hobbit");
        assertThat(remaining).isEmpty();
    }
}
