package group5.webapp.FinalProjectOOP.repositories;

import group5.webapp.FinalProjectOOP.models.CustomerInfo;
import group5.webapp.FinalProjectOOP.models.User;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Integer> {
    CustomerInfo findByUser(User user);

    CustomerInfo findByEmail(String email);

}
