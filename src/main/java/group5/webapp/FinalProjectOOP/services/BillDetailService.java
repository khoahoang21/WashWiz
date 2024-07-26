package group5.webapp.FinalProjectOOP.services;

import group5.webapp.FinalProjectOOP.models.Bill;
import group5.webapp.FinalProjectOOP.models.BillDetail;
import group5.webapp.FinalProjectOOP.models.Product;
import group5.webapp.FinalProjectOOP.models.keys.BillDetailKey;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BillDetailService {
    Optional<BillDetail> findBillDetailsByProductIdAndBillId(Product productId, Bill billId);
    List<BillDetail> findAllByBillId(Bill billId);

    void saveBillDetail(BillDetail billDetail);

    void removeBillDetail(BillDetailKey billDetailKey);

    List<BillDetail> findAll();

    Page<BillDetail> PagingAllBillDetail(int offset, int pageSize);

    void deleteBillDetail(Bill bill, Product product);
}
