package space.gavinklfong.demo.streamapi;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import space.gavinklfong.demo.streamapi.models.Customer;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.CustomerRepo;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;

@Slf4j
@Component
public class AppCommandRunner implements CommandLineRunner {

	@Autowired
	private CustomerRepo customerRepos;
	
	@Autowired
	private OrderRepo orderRepos;
	
	@Autowired
	private ProductRepo productRepos;

	
	@Override
	public void run(String... args) throws Exception {
		log.info("running runner");
		List<Customer> customers = customerRepos.findAll();
		customers.forEach(System.out::println);
		
		List<Order> orders = orderRepos.findAll();
				
		
//		orders.stream()
//		.map(o -> o.getCustomer())
//		.forEach(i -> System.out.println(i.getName()));
		
//		log.info("Customer count: " + count);

//		orders.stream()
//		.flatMap(o -> o.getProducts().stream())
//		.forEach(System.out::println);
		
		
		List<Product> products = productRepos.findAll();
		products.stream()
		.flatMap(p -> p.getOrders().stream())
		.forEach(System.out::println);
	}

}
