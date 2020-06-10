package local.jren.crudyorders.services;

import local.jren.crudyorders.models.Customer;
import local.jren.crudyorders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        customerRepository.findAll().iterator().forEachRemaining(customerList::add);
        return customerList;
    }

    @Override
    public Customer findCustomerById(long id) {
        return customerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Customer "+id+" Not Found"));
    }

    @Override
    public List<Customer> findByNameLike(String thename) {
        return customerRepository.findByCustnameContainingIgnoringCase(thename);
    }
}
