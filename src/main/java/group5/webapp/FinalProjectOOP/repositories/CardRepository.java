package group5.webapp.FinalProjectOOP.repositories;

import group5.webapp.FinalProjectOOP.models.Card;
import group5.webapp.FinalProjectOOP.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {


    List<Card> findAllByUser(User user);
}
