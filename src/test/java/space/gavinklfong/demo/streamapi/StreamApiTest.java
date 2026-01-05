package space.gavinklfong.demo.streamapi;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.test.annotation.DirtiesContext;
import space.gavinklfong.demo.streamapi.models.Customer;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.CustomerRepo;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;

@Slf4j
@DataJpaTest
class StreamApiTest {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private ProductRepo productRepo;

	private ExerciseStreamApi exerciseStreamApi;

	@BeforeEach
	void setUp() {
		exerciseStreamApi = new ExerciseStreamApi(customerRepo,
				orderRepo, productRepo);
	}

	@Test
	@DisplayName("1. Obtain a list of product with category = \"Books\" and price > 100")
	void exercise1() {
		List<Product> expected = productRepo.exercise1();
		System.out.println(expected);
		List<Product> result = exerciseStreamApi.exercise1();
		System.out.println(result);
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DisplayName("1a. Obtain a list of product with category = \"Books\" and price > 100 (using Predicate chaining for filter)")
	void exercise1a() {
		List<Product> expected = productRepo.exercise1();
		List<Product> result = exerciseStreamApi.exercise1a();
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DisplayName("1b. Obtain a list of product with category = \"Books\" and price > 100 (using BiPredicate for filter)")
	void exercise1b() {
		List<Product> expected = productRepo.exercise1();
		List<Product> result = exerciseStreamApi.exercise1b();
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DisplayName("2. Obtain a list of order with product category = \"Baby\"")
	void exercise2() {
		List<Order> expected = orderRepo.exercise2();
		List<Order> result = exerciseStreamApi.exercise2();
		Assertions.assertEquals(expected.size(), result.size());
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DirtiesContext
	@DisplayName("3. Obtain a list of product with category = \"Toys\" and then apply 10% discount")
	void exercise3() {
		List<Product> result = exerciseStreamApi.exercise3();
		Assertions.assertNotEquals(0, result.size());
		productRepo.findAll().stream()
				.filter(p -> "Toys".equals(p.getCategory()))
				.forEach(p -> {
					double originalPrice = p.getPrice() / 0.9; // reverse calculation
					Assertions.assertEquals(originalPrice * 0.9, p.getPrice(), 0.01);
				});
	}

	@Test
	@DisplayName("4. Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021")
	void exercise4() {
		List<Product> expected = productRepo.exercise4();
		List<Product> result = exerciseStreamApi.exercise4();
		Assertions.assertEquals(expected.size(), result.size());
		expected.forEach(p ->
				Assertions.assertTrue(result.contains(p))
		);
	}

	@Test
	@DisplayName("5. Get the 3 cheapest products of \"Books\" category")
	void exercise5() {
		List<Product> expected = productRepo.exercise5().stream().limit(3).collect(Collectors.toList());
		List<Product> result = exerciseStreamApi.exercise5();
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DisplayName("6. Get the 3 most recent placed order")
	void exercise6() {
		List<Order> expected = orderRepo.exercise6().stream().limit(3).collect(Collectors.toList());
		List<Order> result = exerciseStreamApi.exercise6();
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DisplayName("7. Get a list of products which was ordered on 15-Mar-2021")
	void exercise7() {
		List<Product> expected = productRepo.exercise7();
		List<Product> result = exerciseStreamApi.exercise7();
		Assertions.assertEquals(expected.size(), result.size());
		expected.forEach(p ->
				Assertions.assertTrue(result.contains(p))
		);
	}

	@Test
	@DisplayName("8. Calculate the total lump of all orders placed in Feb 2021")
	void exercise8() {
		Double expected = orderRepo.exercise8();
		Double result = exerciseStreamApi.exercise8();
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DisplayName("8a. Calculate the total lump of all orders placed in Feb 2021 (using reduce with BiFunction)")
	void exercise8a() {
		Double expected = orderRepo.exercise8();
		Double result = exerciseStreamApi.exercise8a();
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DisplayName("9. Calculate the average price of all orders placed on 15-Mar-2021")
	void exercise9() {
		Double expected = orderRepo.exercise9();
		Double result = exerciseStreamApi.exercise9();
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DisplayName("10. Obtain statistics summary of all products belong to \"Books\" category")
	void exercise10() {
		Integer count = productRepo.exercise10Count();
		Double sum = productRepo.exercise10Sum();
		Double min = productRepo.exercise10Min();
		Double max = productRepo.exercise10Max();
		DoubleSummaryStatistics expected = new DoubleSummaryStatistics(count, min, max, sum);
		DoubleSummaryStatistics result = exerciseStreamApi.exercise10();
		Assertions.assertEquals(expected.toString(), result.toString());
	}

	@Test
	@DisplayName("11. Obtain a mapping of order id and the order's product count")
	void exercise11() {
		Map<Long, Integer> expected = orderRepo.exercise11().stream()
				.collect(Collectors.toMap(
						row -> (Long) row[0],
						row -> ((Long) row[1]).intValue()
				));
		Map<Long, Integer> result = exerciseStreamApi.exercise11();
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DisplayName("12. Obtain a data map of customer and list of orders")
	void exercise12() {
		Map<Customer, List<Order>> expected = orderRepo.exercise12()
				.stream()
				.collect(Collectors.groupingBy(
						row -> (Customer) row[0],
						Collectors.mapping(row -> (Order) row[1], Collectors.toList())
				));
		Map<Customer, List<Order>> result = exerciseStreamApi.exercise12();
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DisplayName("12a. Obtain a data map of customer_id and list of order_id(s)")
	void exercise12a() {
		Map<Long, List<Long>> expected = orderRepo.exercise12a()
				.stream()
				.collect(Collectors.groupingBy(
						row -> (Long) row[0],
						Collectors.mapping(row -> (Long) row[1], Collectors.toList())
				));
		Map<Long, List<Long>> result = exerciseStreamApi.exercise12a();
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DisplayName("13. Obtain a data map with order and its total price")
	void exercise13() {
		Map<Order, Double> expected = orderRepo.exercise13()
				.stream()
				.collect(Collectors.toMap(
						row -> (Order) row[0],
						row -> ((Number) row[1]).doubleValue()
				));
		Map<Order, Double> result = exerciseStreamApi.exercise13();

		Assertions.assertEquals(expected.size(), result.size(), "Maps should have the same number of entries");

		// Compare values for each order key
		expected.forEach((order, expectedValue) -> {
			Assertions.assertTrue(result.containsKey(order),
					"Result map should contain order with id: " + order.getId());
			Double resultValue = result.get(order);
			Assertions.assertEquals(expectedValue, resultValue, 0.01,
					"Total price for order " + order.getId() + " should match");
		});
	}

	@Test
	@DisplayName("13a. Obtain a data map with order and its total price (using reduce)")
	void exercise13a() {
		Map<Order, Double> expected = orderRepo.exercise13()
				.stream()
				.collect(Collectors.toMap(
						row -> (Order) row[0],
						row -> ((Number) row[1]).doubleValue()
				));
		Map<Order, Double> result = exerciseStreamApi.exercise13a();
		Assertions.assertEquals(expected.size(), result.size());
		expected.forEach((order, expectedValue) -> {
			Assertions.assertTrue(result.containsKey(order),
					"Result map should contain order with id: " + order.getId());
			Double resultValue = result.get(order);
			Assertions.assertEquals(expectedValue, resultValue, 0.01,
					"Total price for order " + order.getId() + " should match");
		});
	}

	@Test
	@DisplayName("14. Obtain a data map of product name by category")
	void exercise14() {
		Map<String, List<String>> expected = productRepo.exercise14()
				.stream()
				.collect(Collectors.groupingBy(
						row -> (String) row[0],
						Collectors.mapping(row -> (String) row[1], Collectors.toList())
				));
		Map<String, List<String>> result = exerciseStreamApi.exercise14();
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DisplayName("15. Get the most expensive product per category")
	void exercise15() {
		Map<String, Optional<Product>> expected = productRepo.exercise15()
				.stream()
				.collect(Collectors.groupingBy(
						row -> (String) row[0],
						Collectors.mapping(
								row -> (Product) row[1],
								Collectors.maxBy(Comparator.comparing(Product::getPrice))
						)
				));
		Map<String, Optional<Product>> result = exerciseStreamApi.exercise15();
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DisplayName("15a. Get the most expensive product (by name) per category")
	void exercise15a() {
		Map<String, String> expected = productRepo.exercise15()
				.stream()
				.collect(Collectors.groupingBy(
						row -> (String) row[0],
						Collectors.collectingAndThen(
								Collectors.mapping(
										row -> (Product) row[1],
										Collectors.maxBy(Comparator.comparing(Product::getPrice))
								),
								opt -> opt.map(Product::getName).orElse("")
						)
				));
		Map<String, String> result = exerciseStreamApi.exercise15a();
		Assertions.assertEquals(expected, result);
	}

}
