package group5.webapp.FinalProjectOOP.repositories;

import group5.webapp.FinalProjectOOP.models.Product;
import group5.webapp.FinalProjectOOP.models.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    ProductDetail findByProduct(Product product);

    void deleteProductDetailById(int id);
}
