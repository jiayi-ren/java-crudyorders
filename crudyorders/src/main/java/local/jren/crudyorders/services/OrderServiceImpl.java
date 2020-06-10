package local.jren.crudyorders.services;

import local.jren.crudyorders.models.Customer;
import local.jren.crudyorders.models.Order;
import local.jren.crudyorders.models.Payment;
import local.jren.crudyorders.repositories.CustomerRepository;
import local.jren.crudyorders.repositories.OrderRepository;
import local.jren.crudyorders.repositories.PaymentRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Order findOrderById(long id) {
        return orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Order "+id+" Not Found"));
    }

    @Override
    public List<Order> findOrdersWithAdvanceAmount(double advance) {
        return orderRepository.findOrdersByAdvanceamountIsGreaterThan(advance);
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Order "+id+" Not Found");
        }
    }

    @Transactional
    @Override
    public Order save(Order order) {
        Order newOrder = new Order();

        if (order.getOrdnum() != 0) {
            // put
            orderRepository.findById(order.getOrdnum()).orElseThrow(()-> new EntityNotFoundException("Order "+order.getOrdnum()+" Not Found"));
            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setOrderdescription(order.getOrderdescription());

        Customer customer = customerRepository.findById(order.getCustomer().getCustcode()).orElseThrow(()->new EntityNotFoundException("Customer "+order.getCustomer().getCustcode()+" Not Found"));
        newOrder.setCustomer(customer);

        newOrder.getPayments().clear();
        for (Payment payment: order.getPayments()) {
            Payment newPayment = paymentRepository.findById(payment.getPaymentid()).orElseThrow(()-> new EntityNotFoundException("Payment "+payment.getPaymentid()+" Not Found"));
            newOrder.addPayments(newPayment);
        }

        return orderRepository.save(newOrder);
    }
}
