package group5.webapp.FinalProjectOOP.repositories;

import group5.webapp.FinalProjectOOP.models.Address;
import group5.webapp.FinalProjectOOP.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddresRepository extends JpaRepository<Address, Integer> {

    List<Address> findAllByUser(User user);

}
