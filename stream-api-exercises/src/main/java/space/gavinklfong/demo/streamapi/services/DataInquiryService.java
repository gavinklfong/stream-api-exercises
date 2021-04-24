package space.gavinklfong.demo.streamapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.CustomerRepo;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;

@Service
public class DataInquiryService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	//
	// Extract products with category = [category] and price > [price]
	//
	public List<Product> question1(String category, Double price) {
		
		return productRepo.findAll()
		.stream()
		.filter(p -> p.getCategory().equalsIgnoreCase(category))
		.filter(p -> p.getPrice() > price)
		.collect(Collectors.toList());
		
	}
	
	//
	// Obtain a list of order contains product with specific category
	//
	public List<Order> question2(String category) {
		
		return orderRepo.findAll()
				.stream()
				.filter(o -> 
					o.getProducts()
					.stream()
					.anyMatch(p -> p.getCategory().equalsIgnoreCase(category))
				)
				.collect(Collectors.toList());	
	}
	
	//
	// Obtain a list of product with category = “Women Clothing” and apply 10% discount
	//
	public List<Product> question3(String category) {
		
		return productRepo.findAll()
				.stream()
				.filter(p -> p.getCategory().equalsIgnoreCase(category))
				.map(p -> p.withPrice(p.getPrice() * 1.1))
				.collect(Collectors.toList());	
	}
	
	//
	// Obtain a list of orders which are placed by customer with tier = 3
	//
	public List<Order> question4(int tier) {
		
		return orderRepo.findAll()
		.stream()
		.filter(o -> o.getCustomer().getTier() == tier)
		.collect(Collectors.toList());
	}
	
	//
	// Get the most expensive product of “Cookware” category

	//
	public Product question5(String category) {
		
		return productRepo.findAll()
				.stream()
				.
		
	}
}
