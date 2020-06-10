package local.jren.crudyorders.services;

import local.jren.crudyorders.models.Order;
import local.jren.crudyorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findOrderById(long id) {
        return orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Order "+id+" Not Found"));
    }

    @Override
    public List<Order> findOrdersWithAdvanceAmount(double advance) {
        return orderRepository.findOrdersByAdvanceamountIsGreaterThan(advance);
    }
}
