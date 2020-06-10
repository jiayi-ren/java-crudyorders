package local.jren.crudyorders.services;

import local.jren.crudyorders.models.Agent;
import local.jren.crudyorders.models.Customer;
import local.jren.crudyorders.models.Order;
import local.jren.crudyorders.models.Payment;
import local.jren.crudyorders.repositories.CustomerRepository;
import local.jren.crudyorders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

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

    @Transactional
    @Override
    public void delete(long id) {
        if (customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer "+id+" Not Found");
        }
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        Customer newCustomer = new Customer();
        if (customer.getCustcode() != 0) {
            // put
            customerRepository.findById(customer.getCustcode()).orElseThrow(()-> new EntityNotFoundException("Customer "+customer.getCustcode()+" Not Found"));
            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());

        newCustomer.setAgent(customer.getAgent());

        newCustomer.getOrders().clear();
        for (Order order: customer.getOrders()) {
            List<Payment> paymentList = new ArrayList<>();
            for (Payment payment: order.getPayments()){
                Payment newPayment = paymentRepository.findById(payment.getPaymentid()).orElseThrow(()-> new EntityNotFoundException("Payment "+payment.getPaymentid()+" Not Found"));
                paymentList.add(newPayment);
            }
            Order newOrder = new Order(order.getAdvanceamount(),order.getOrdamount(), order.getOrderdescription(), newCustomer, paymentList);
            newCustomer.getOrders().add(newOrder);
        }

        return customerRepository.save(newCustomer);
    }

    @Transactional
    @Override
    public Customer update(Customer customer, long id) {
        Customer currentCustomer = customerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Customer "+id+" Not Found"));

        if(customer.getCustcode() != 0) {
            customerRepository.findById(customer.getCustcode()).orElseThrow(()-> new EntityNotFoundException("Customer "+customer.getCustcode()+" Not Found"));
            currentCustomer.setCustcode(customer.getCustcode());
        }

        if(customer.getCustname() != null) {
            currentCustomer.setCustname(customer.getCustname());
        }

        if(customer.getCustcity() != null) {
            currentCustomer.setCustcity(customer.getCustcity());
        }

        if(customer.getCustcountry() != null) {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }

        if(customer.getWorkingarea() != null) {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }

        if(customer.getGrade() != null) {
            currentCustomer.setGrade(customer.getGrade());
        }

        if(customer.getPhone() != null) {
            currentCustomer.setPhone(customer.getPhone());
        }

        if(customer.hasopeningamt) {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if(customer.hasreceiveamt) {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if(customer.haspaymentamt) {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if(customer.hasoutstandingamt) {
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }

        if(customer.getAgent() != null) {
            currentCustomer.setAgent(customer.getAgent());
        }

        if(customer.getOrders().size() > 0) {
            currentCustomer.getOrders().clear();
            for (Order order: customer.getOrders()) {
                List<Payment> paymentList = new ArrayList<>();
                for (Payment payment: order.getPayments()){
                    Payment newPayment = paymentRepository.findById(payment.getPaymentid()).orElseThrow(()-> new EntityNotFoundException("Payment "+payment.getPaymentid()+" Not Found"));
                    paymentList.add(newPayment);
                }
                Order newOrder = new Order(order.getAdvanceamount(),order.getOrdamount(), order.getOrderdescription(), currentCustomer, paymentList);
                currentCustomer.getOrders().add(newOrder);
            }
        }

        return customerRepository.save(currentCustomer);
    }
}
