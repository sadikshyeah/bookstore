package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			log.info("save a couple of books");
			repository.save(new Book("1984", "George Orwell", 1949, "978-0451524935", 12.99));

			repository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "978-0061120084", 14.50));

			repository.save(new Book("The Hobbit", "J.R.R. Tolkien", 1937, "978-0547928227", 18.99));

			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			log.info("find a book by title");
			for (Book book : repository.findByTitle("1984")) {
				log.info(book.toString());
			}

		};
	}
}
