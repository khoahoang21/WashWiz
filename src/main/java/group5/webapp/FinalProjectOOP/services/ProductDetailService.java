package group5.webapp.FinalProjectOOP.services;

import group5.webapp.FinalProjectOOP.models.Product;
import group5.webapp.FinalProjectOOP.models.ProductDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductDetailService {
    ProductDetail findByProduct(Product product);

    List<ProductDetail> findAll();

    Page<ProductDetail> PagingAllProductDetail(int offset, int pageSize);

    void save(ProductDetail productDetail);

    ProductDetail getProductDetailById(Integer id);

    void deleteProductDetail(int id);
}
