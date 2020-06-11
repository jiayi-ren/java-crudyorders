package local.jren.crudyorders.repositories;

import local.jren.crudyorders.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
