package geektime.spring.data.redisdemo.repository;

import geektime.spring.data.redisdemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;


public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
