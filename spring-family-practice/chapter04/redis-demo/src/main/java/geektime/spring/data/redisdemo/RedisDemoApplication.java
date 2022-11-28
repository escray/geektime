package geektime.spring.data.redisdemo;

import geektime.spring.data.redisdemo.service.CoffeeService;

import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.Map;

@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories
@SpringBootApplication
public class RedisDemoApplication implements ApplicationRunner {

	@Autowired
	private CoffeeService coffeeService;
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private JedisPoolConfig jedisPoolConfig;


	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(RedisDemoApplication.class);
		application.setAllowCircularReferences(true);
		application.run(args);
//		SpringApplication.run(RedisDemoApplication.class, args);
	}

	@Bean
	@ConfigurationProperties("redis")
	public JedisPoolConfig jedisPoolConfig() {
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(128);
		poolConfig.setMaxIdle(128);
		poolConfig.setMinIdle(16);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);
		poolConfig.setTestWhileIdle(true);

		poolConfig.setMinEvictableIdleTime(Duration.ofSeconds(60));
		poolConfig.setTimeBetweenEvictionRuns(Duration.ofSeconds(30));

		poolConfig.setNumTestsPerEvictionRun(3);
		poolConfig.setBlockWhenExhausted(true);

		poolConfig.setJmxEnabled(false);

		return poolConfig;
	}

	@Bean(destroyMethod = "close")
	public JedisPool jedisPool(@Value("${redis.host}") String host) {
		return new JedisPool(jedisPoolConfig());
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("jedisPoolConfig: {}", jedisPoolConfig.toString());
		try (Jedis jedis = jedisPool.getResource()) {
			coffeeService.findAllCoffee().forEach(c -> {
				jedis.hset("spring-menu",
						c.getName(),
						Long.toString(c.getPrice().getAmountMinorLong()));
			});

			Map<String, String> menu = jedis.hgetAll("springbucks-menu");
			log.info("Menu: {}", menu);

			String price = jedis.hget("springbucks-menu", "espresso");
			log.info("espress - {}", Money.ofMinor(CurrencyUnit.of("CNY"), Long.parseLong(price)));

		} catch (Exception e) {
			log.info("======> JedisPool error");
		}

//		JedisExample();
	}

	private void JedisExample() {
		Jedis jedis = new Jedis();

		jedis.set("events/city/rome", "32, 15, 223, 828");
		log.info("events/city/rome {}", jedis.get("events/city/rome"));

		jedis.lpush("queue#tasks", "firstTask");
		jedis.lpush("queue#tasks", "secondTask");
		log.info("queue#tasks: {}", jedis.rpop("queue#tasks"));

		jedis.sadd("nicknames", "nickname#1");
		jedis.sadd("nicknames", "nickname#2");
		jedis.sadd("nicknames", "nickname#2");

		log.info("nicknames: {}", jedis.smembers("nicknames"));
		log.info(String.valueOf(jedis.sismember("nicknames", "nickname#1")));

		final JedisPoolConfig poolConfig = jedisPoolConfig();
		JedisPool jedisPool = new JedisPool(poolConfig);
		try (Jedis j = jedisPool.getResource()) {
			log.info("======success======");
			j.hset("user#1", "name", "Peter");
			j.hset("user#1", "job", "politician");
			log.info(j.hget("user#1", "name"), j.hgetAll("user#1").get("job"));
		} catch (Exception e) {
			log.info("error");
		}
	}
}
