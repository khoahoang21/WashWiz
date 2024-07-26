package group5.webapp.FinalProjectOOP.repositories;

import group5.webapp.FinalProjectOOP.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAll();

    void deleteCategoryById(int id);
}
