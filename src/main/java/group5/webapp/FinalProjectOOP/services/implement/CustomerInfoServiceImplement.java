package group5.webapp.FinalProjectOOP.services.implement;

import group5.webapp.FinalProjectOOP.models.CustomerInfo;
import group5.webapp.FinalProjectOOP.models.User;
import group5.webapp.FinalProjectOOP.repositories.CustomerInfoRepository;
import group5.webapp.FinalProjectOOP.services.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CustomerInfoServiceImplement implements CustomerInfoService {

    @Autowired
    CustomerInfoRepository customerInfoRepository;

    @Override
    public CustomerInfo findByUser(User user) {
        return customerInfoRepository.findByUser(user);
    }

    @Override
    public CustomerInfo findByEmail(String email) {
        return customerInfoRepository.findByEmail(email);
    }

    @Override
    public List<CustomerInfo> findAll() {
        return customerInfoRepository.findAll();
    }

    @Override
    public CustomerInfo findCustomerInfoByUserID(Integer id) {
        return customerInfoRepository.getById(id);
    }

    @Override
    public void saveInfo(CustomerInfo info) {
        customerInfoRepository.save(info);
    }

    @Override
    public void deletInfoById(Integer id) {
        customerInfoRepository.deleteById(id);
    }

    @Override
    public Page<CustomerInfo> pagingCustomerInfo(int offset, int pagesize) {
        return customerInfoRepository.findAll(PageRequest.of(offset, pagesize, Sort.by("id").descending()));
    }

    @Override
    public CustomerInfo getByID(Integer id) {
        return customerInfoRepository.getById(id);
    }

    @Override
    public CustomerInfo registerV2(CustomerInfo c, User u) {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setFullname(c.getFullname());
        customerInfo.setEmail(c.getEmail());
        customerInfo.setPhone(c.getPhone());
        customerInfo.setUser(u);
        return customerInfoRepository.save(customerInfo);
    }
}
