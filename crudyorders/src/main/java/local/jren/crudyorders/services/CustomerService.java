package local.jren.crudyorders.services;

import local.jren.crudyorders.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustomers();
    Customer findCustomerById(long id);
    List<Customer> findByNameLike(String thename);
    void delete(long id);
    Customer save(Customer customer);
    Customer update(Customer customer, long id);
}
