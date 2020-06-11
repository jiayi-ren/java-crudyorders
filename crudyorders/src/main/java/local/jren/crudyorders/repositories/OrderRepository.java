package local.jren.crudyorders.repositories;

import local.jren.crudyorders.models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findOrdersByAdvanceamountIsGreaterThan(double advance);
}
