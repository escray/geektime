package geektime.spring.data.redisdemo.repository;

import geektime.spring.data.redisdemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
