package group5.webapp.FinalProjectOOP;

import group5.webapp.FinalProjectOOP.models.*;
import group5.webapp.FinalProjectOOP.models.GetData.GetData;
import group5.webapp.FinalProjectOOP.repositories.*;
import group5.webapp.FinalProjectOOP.services.BillDetailService;
import group5.webapp.FinalProjectOOP.services.ProductService;
import group5.webapp.FinalProjectOOP.services.implement.ProductServiceImplement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class FinalProjectOopApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductService productService;

	@Autowired
	public static CategoryRepository categoryRepository;

	@Autowired
	ProductDetailRepository productDetailRepository;

	@Autowired
	BillRepository billRepository;

	@Autowired
	BillDetailService billDetailService;

	@Test
	void contextLoads() {

	}

}
