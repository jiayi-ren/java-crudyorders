package local.jren.crudyorders.controllers;

import local.jren.crudyorders.models.Order;
import local.jren.crudyorders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // http://localhost:2019/orders/order/7
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> findOrderById(@PathVariable long id) {
        Order order = orderService.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // http://localhost:2019/orders/advanceamount
    @GetMapping(value = "/advanceamount", produces = {"application/json"})
    public ResponseEntity<?> findOrdersWithAdvanceAmount() {
        List<Order> orders = orderService.findOrdersWithAdvanceAmount(0);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
