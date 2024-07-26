package group5.webapp.FinalProjectOOP.repositories;

import group5.webapp.FinalProjectOOP.models.BillDetail;
import group5.webapp.FinalProjectOOP.models.keys.BillDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDetailRepository extends JpaRepository<BillDetail, BillDetailKey> {
}
