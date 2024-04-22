package com.njema.orderservice;

import com.njema.orderservice.entities.Order;
import com.njema.orderservice.entities.ProductItem;
import com.njema.orderservice.enums.orderStatus;
import com.njema.orderservice.model.Customer;
import com.njema.orderservice.model.Product;
import com.njema.orderservice.repositories.OrderRepository;
import com.njema.orderservice.repositories.ProductItemRepository;
import com.njema.orderservice.services.CustomerRestClientService;
import com.njema.orderservice.services.InventoryRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(OrderRepository orderRepository,
							ProductItemRepository productItemRepository,
							CustomerRestClientService customerRestClientService,
							InventoryRestClientService inventoryRestClientService){
		return args -> {
			List<Customer> customers=customerRestClientService.allCustomers().getContent().stream().toList();
			List<Product> products=inventoryRestClientService.allProducts().getContent().stream().toList();
			Long customerId=1L;
			Customer customer=customerRestClientService.customerById(customerId);
			Random random=new Random();
			for(int i=0;i<20;i++){
				Order order= Order.builder()
						.CustomerId(customers.get(random.nextInt(customers.size())).getId())
						.status(Math.random()>0.5?orderStatus.PENDING:orderStatus.CREATED)
						.createdAt(new Date())
						.build();
				Order savedOrder=orderRepository.save(order);
				for(int j=0;j<products.size();j++){
					if(Math.random()>0.70){ProductItem productItem= ProductItem.builder()
							.order(savedOrder)
							.productId(products.get(j).getId())
							.price(products.get(j).getPrice())
							.quantity(1+random.nextInt(10))
							.discount(Math.random())
							.build();
						productItemRepository.save(productItem);
					}

				}
			}

		};
	}
}
