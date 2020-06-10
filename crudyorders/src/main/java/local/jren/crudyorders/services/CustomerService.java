package local.jren.crudyorders.services;

import local.jren.crudyorders.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustomers();
    Customer findCustomerById(long id);
    List<Customer> findByNameLike(String thename);
}
