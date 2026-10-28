package sg.edu.ntu.simple_crm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
//import java.util.ArrayList;
import java.util.List;

//import sg.edu.ntu.simple_crm.exceptions.CustomerNotFoundException;
import sg.edu.ntu.simple_crm.model.Customer;
import sg.edu.ntu.simple_crm.model.Interaction;

import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.ntu.simple_crm.service.CustomerService;
//import sg.edu.ntu.simple_crm.service.CustomerServiceImpl;

// mvn clean spring-boot:run

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Create a new customer
    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid Customer customer) {
        Customer newCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    // Retrieve all customers
    @GetMapping("")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    // Retrieve a specific customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
            Customer foundCustomer = customerService.getCustomer(id);
            return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }

    // Update a specific customer by ID
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
      
            Customer updatedCustomer = customerService.updateCustomer(id, customer);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
       

    }

    // Delete a specific customer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {

            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        

    }

    @PostMapping("/{id}/interactions")
    public ResponseEntity<Interaction> addInteractionToCustomer(
            @PathVariable Long id,
            @RequestBody Interaction interaction) {
        Interaction newInteraction = customerService.addInteractionToCustomer(id, interaction);
        return new ResponseEntity<>(newInteraction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/interactions")
    public ResponseEntity<List<Interaction>> getInteractionsForCustomer(@PathVariable Long id) {

            Customer customer = customerService.getCustomer(id);
            List<Interaction> interactions = customer.getInteractions();
            return new ResponseEntity<>(interactions, HttpStatus.OK);

    }

    @DeleteMapping("/{id}/interactions/{interactionId}")
    public ResponseEntity<HttpStatus> deleteInteractionFromCustomer(
            @PathVariable Long id,
            @PathVariable Long interactionId) {

            customerService.deleteInteractionFromCustomer(id, interactionId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomers(@RequestParam String firstName) {
        List<Customer> foundCustomers = customerService.searchCustomers(firstName);
        return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
    }

    @GetMapping("/search/job")
    public ResponseEntity<List<Customer>> searchCustomersByJobTitle(@RequestParam String jobTitle) {
        List<Customer> foundCustomers = customerService.searchCustomersByJobTitle(jobTitle);
        return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
    }
}
