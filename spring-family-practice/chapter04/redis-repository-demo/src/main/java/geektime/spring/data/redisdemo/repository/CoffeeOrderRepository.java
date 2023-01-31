package geektime.spring.data.redisdemo.repository;

import geektime.spring.data.redisdemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;


public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
