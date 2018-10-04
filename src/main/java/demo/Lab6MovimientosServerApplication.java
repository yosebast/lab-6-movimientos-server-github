package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages={"demo", "org.test.beans", "org.test.controller", "org.test.dao", "org.test.exception", "org.test.jdbc", "org.test.model", "org.test.service", "org.test.modelo", "org.test.bean"})
@EnableDiscoveryClient
public class Lab6MovimientosServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab6MovimientosServerApplication.class, args);
	}
	
	
	 @LoadBalanced
		@Bean
		RestTemplate restTemplate() {
			return new RestTemplate();
		}
}
