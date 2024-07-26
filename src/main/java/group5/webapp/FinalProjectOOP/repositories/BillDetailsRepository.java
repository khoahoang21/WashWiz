package group5.webapp.FinalProjectOOP.repositories;
import group5.webapp.FinalProjectOOP.models.Bill;
import group5.webapp.FinalProjectOOP.models.BillDetail;
import group5.webapp.FinalProjectOOP.models.Product;
import group5.webapp.FinalProjectOOP.models.keys.BillDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BillDetailsRepository extends JpaRepository<BillDetail, BillDetailKey> {
    Optional<BillDetail> findBillDetailsByProductIdAndBillId(Product productId, Bill billId);

    List<BillDetail> findAllByBillId(Bill billId);

    List<BillDetail> findAll();

    void deleteBillDetailByBillIdAndProductId(Bill bill, Product product);
}