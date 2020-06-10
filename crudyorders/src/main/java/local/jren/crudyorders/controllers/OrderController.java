package local.jren.crudyorders.controllers;

import local.jren.crudyorders.models.Order;
import local.jren.crudyorders.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // GET http://localhost:2019/orders/order/7
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> findOrderById(@PathVariable long id) {
        Order order = orderService.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // GET http://localhost:2019/orders/advanceamount
    @GetMapping(value = "/advanceamount", produces = {"application/json"})
    public ResponseEntity<?> findOrdersWithAdvanceAmount() {
        List<Order> orders = orderService.findOrdersWithAdvanceAmount(0);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // DELETE http://localhost:2019/orders/order/5
    @DeleteMapping(value = "/order/{ordnum}")
    public ResponseEntity<?> deleteOrder(@PathVariable long ordnum) {
        orderService.delete(ordnum);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // POST http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes = {"application/json"})
    public ResponseEntity<?> addOrder(@Valid @RequestBody Order newOrder) {
        newOrder.setOrdnum(0);
        newOrder = orderService.save(newOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        // http://localhost:2019/orders/order/newId
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ordnum}").buildAndExpand(newOrder.getOrdnum()).toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/orders/order/10
    @PutMapping(value = "/order/{ordnum}", consumes = {"application/json"})
    public ResponseEntity<?> updateFullOrder(@RequestBody Order updateOrder, @PathVariable long ordnum) {
        updateOrder.setOrdnum(ordnum);
        orderService.save(updateOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
