package sg.edu.ntu.simple_crm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import sg.edu.ntu.simple_crm.model.Customer;
import sg.edu.ntu.simple_crm.repository.CustomerRepository;

@Component
public class DataLoader {

    private CustomerRepository customerRepository;

    @Autowired
    public DataLoader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostConstruct
    public void loadData() {
        customerRepository.deleteAll();
        customerRepository.save(new Customer("Tony", "Stark", "tony.stark@example.com"));
        customerRepository.save(new Customer("Bruce", "Banner", "bruce.banner@example.com"));
        customerRepository.save(new Customer("Peter", "Parker", "peter.parker@example.com"));
        customerRepository.save(new Customer("Stephen", "Strange", "stephen.strange@example.com"));

    }

}
