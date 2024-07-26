package group5.webapp.FinalProjectOOP.services.implement;

import group5.webapp.FinalProjectOOP.models.Address;
import group5.webapp.FinalProjectOOP.models.Card;
import group5.webapp.FinalProjectOOP.models.User;
import group5.webapp.FinalProjectOOP.repositories.CardRepository;
import group5.webapp.FinalProjectOOP.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CardServiceImplement implements CardService {
    @Autowired
    CardRepository cardRepository;
    @Override
    public List<Card> findAllByUser(User user) {
        return cardRepository.findAllByUser(user);
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card getById(Integer id) {
        return cardRepository.getById(id);
    }

    @Override
    public void deleteCardById(Integer id) {
        cardRepository.deleteById(id);
    }

    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);
    }

    @Override
    public Page<Card> pagingCard(int offset, int pagesize) {
        return cardRepository.findAll(PageRequest.of(offset, pagesize, Sort.by("id").descending()));
    }
}
