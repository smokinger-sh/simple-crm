package sg.edu.ntu.simple_crm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sg.edu.ntu.simple_crm.exceptions.CustomerNotFoundException;
import sg.edu.ntu.simple_crm.model.Customer;
import sg.edu.ntu.simple_crm.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void createCustomer_validCustomer_returnSavedCustomer() {
        // 1: ARRANGE
        Customer customer = Customer.builder()
                .firstName("Clint").lastName("Barton")
                .email("clint@avengers.com").contactNo("12345678")
                .jobTitle("Special Agent").yearOfBirth(1975)
                .build();

        // Program the mock.
        when(customerRepository.save(customer)).thenReturn(customer);

        // 2: ACT
        Customer savedCustomer = customerService.createCustomer(customer);

        // 3: ASSERT
        assertEquals(customer, savedCustomer, " The saved customer should be same as actual customer");
        verify(customerRepository, times(1)).save(customer);

    }

    @Test 
    public void getCustomer_missingId_throwsCustomerNotFoundException()
    {
        // 1 ARRANGE
        Long customerId = 1L;

        // Optional.empty()
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        //2: ACT + 3. ASSERT
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomer(customerId));


    }



}
