package local.jren.crudyorders.services;

import local.jren.crudyorders.models.Order;

import java.util.List;

public interface OrderService {
    Order findOrderById(long id);
    List<Order> findOrdersWithAdvanceAmount(double advance);
    void delete(long id);
    Order save(Order order);
}
