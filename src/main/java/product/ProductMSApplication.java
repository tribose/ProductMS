package product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductMSApplication {

	public static void main(String[] args) {
		
		System.setProperty("spring.config.name", "products-server");
		SpringApplication.run(ProductMSApplication.class, args);
	}
}
