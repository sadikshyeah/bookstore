package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@SpringBootTest(classes = BookstoreApplication.class)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void createNewCategory() {
        Category category = new Category("RepositoryTestCategory");
        repository.save(category);
        assertThat(category.getId()).isNotNull();

        assertThat(repository.findById(category.getId())).isPresent();
        assertThat(repository.findById(category.getId()).get().getName())
                .isEqualTo("RepositoryTestCategory");
    }

    @Test
    public void deleteCategory() {
        Category category = new Category("CategoryToDelete");
        repository.save(category);
        Long id = category.getId();
        assertThat(repository.findById(id)).isPresent();

        repository.deleteById(id);

        assertThat(repository.findById(id)).isEmpty();
    }
}
