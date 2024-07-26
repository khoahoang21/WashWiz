package group5.webapp.FinalProjectOOP.repositories;

import group5.webapp.FinalProjectOOP.models.Product;
import group5.webapp.FinalProjectOOP.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    List<ProductImage> findAll();

    List<ProductImage> findAllByProduct(Product product);

    void deleteProductImageById(int id);
}
