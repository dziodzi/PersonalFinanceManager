package ru.rfma.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rfma.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category getCategoryByName(final String name);
}
