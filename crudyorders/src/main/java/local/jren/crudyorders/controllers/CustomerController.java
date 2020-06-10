package local.jren.crudyorders.controllers;

import local.jren.crudyorders.models.Customer;
import local.jren.crudyorders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // GET http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // GET http://localhost:2019/customers/customer/7
    @GetMapping(value = "/customer/{id}", produces = {"application/json"})
    public ResponseEntity<?> findCustomerById(@PathVariable long id) {
        Customer customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // GET http://localhost:2019/customers/namelike/mes
    @GetMapping(value = "/namelike/{thename}", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomersNameLike(@PathVariable String thename) {
        List<Customer> customers = customerService.findByNameLike(thename);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // DELETE http://localhost:2019/customers/customer/7
    @DeleteMapping(value = "/customer/{custcode}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long custcode) {
        customerService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
