package ru.rfma.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rfma.core.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category getCategoryById(int categoryId);

    Category getCategoryByName(String name);
}
