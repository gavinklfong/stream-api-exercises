package space.gavinklfong.demo.streamapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import space.gavinklfong.demo.streamapi.models.Customer;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.CustomerRepo;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Learn how to use Java 8 Stream API
 *
 * @author Minh Ngo
 */
@Component
@RequiredArgsConstructor
public class ExerciseStreamApi {
    private final CustomerRepo customerRepo;

    private final OrderRepo orderRepo;

    private final ProductRepo productRepo;

    public List<Product> exercise1() {
        // TODO: Obtain a list of product with category = "Books" and price > 100
        List<Product> productList = productRepo.findAll();
        return productList.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Books") &&
                        p.getPrice() > 100)
                .collect(Collectors.toList());
    }

    public List<Product> exercise1a() {
        // TODO: Obtain a list of product with category = "Books" and 
        //  price > 100 (using Predicate chaining for filter)
        List<Product> productList = productRepo.findAll();
        Predicate<Product> predicate1 = p -> p.getCategory().equalsIgnoreCase("Books");
        Predicate<Product> predicate2 = p -> p.getPrice() > 100;
        return productList.stream()
                .filter(p -> predicate1.and(predicate2).test(p))
                .collect(Collectors.toList());
    }

    public List<Product> exercise1b() {
        // TODO: Obtain a list of product with category = "Books" and price > 100 (using BiPredicate for filter)
        List<Product> productList = productRepo.findAll();
        BiPredicate<Product, Product> biPredicate = (p1, p2) -> p1.getCategory().equalsIgnoreCase("Books")
                && p2.getPrice() > 100;
        return productList.stream()
                .filter(product -> biPredicate.test(product, product))
                .collect(Collectors.toList());
    }

    public List<Order> exercise2() {
        // TODO: Obtain a list of order with product category = "Baby"
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream()
                .filter(order ->
                    order.getProducts()
                            .stream()
                            .anyMatch(p -> p.getCategory().equalsIgnoreCase("Baby"))
                )
                .collect(Collectors.toList());
    }

    public List<Product> exercise3() {
        // TODO: Obtain a list of product with category = "Toys" and then apply 10% discount
        List<Product> productList = productRepo.findAll();
        return productList.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("Toys"))
                .map(product -> product.withPrice(product.getPrice()*0.9))
                .collect(Collectors.toList());
    }

    public List<Product> exercise4() {
        // TODO: Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
        List<Order> orderList = orderRepo.findAll();
        Predicate<Order> predicateOrder = order -> order.getCustomer().getTier().equals(2)
                && order.getOrderDate().isAfter(LocalDate.of(2021, 2, 1))
                && order.getOrderDate().isBefore(LocalDate.of(2021, 4, 1));
        return orderList.stream()
                .filter(predicateOrder)
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Product> exercise5() {
        // TODO: Get the 3 cheapest products of "Books" category
        List<Product> productList = productRepo.findAll();
        return productList.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
                .sorted(Comparator.comparing(Product::getPrice))
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Order> exercise6() {
        // TODO: Get the 3 most recent placed order
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Product> exercise7() {
        // TODO: Get a list of products which was ordered on 15-Mar-2021
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream()
                .filter(order -> order.getOrderDate().equals(LocalDate.of(2021, 3,15)))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public Double exercise8() {
        // TODO: Calculate the total lump of all orders placed in Feb 2021
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream()
                .filter(order -> order.getOrderDate().getYear() == 2021 && order.getOrderDate().getMonthValue() == 2)
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public Double exercise8a() {
        // TODO: Calculate the total lump of all orders placed in Feb 2021 (using reduce with BiFunction)
        BiFunction<Double, Product, Double> accumulator = (price, product) -> price + product.getPrice();
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream()
                .filter(order -> order.getOrderDate().getYear() == 2021 && order.getOrderDate().getMonthValue() == 2)
                .flatMap(order -> order.getProducts().stream())
                .reduce(0D, accumulator, Double::sum);
    }

    public Double exercise9() {
        // TODO: Calculate the average price of all orders placed on 15-Mar-2021
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream()
                .filter(order -> order.getOrderDate().equals(LocalDate.of(2021, 3, 15)))
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0D);
    }

    public DoubleSummaryStatistics exercise10() {
        // TODO: Obtain statistics summary of all products belong to "Books" category
        List<Product> productList = productRepo.findAll();
        return productList.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("Books"))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();
    }

    public Map<Long, Integer> exercise11() {
        // TODO: Obtain a mapping of order id and the order's product count
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream()
                .collect(Collectors.toMap(Order::getId, order -> order.getProducts().size()));
    }

    public Map<Customer, List<Order>> exercise12() {
        // TODO: Obtain a data map of customer and list of orders
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));
    }

    public Map<Long, List<Long>> exercise12a() {
        // TODO: Obtain a data map of customer_id and list of order_id(s)
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getCustomer().getId(),
                        Collectors.mapping(Order::getId, Collectors.toList())
                ));
    }

    public Map<Order, Double> exercise13() {
        // TODO: Obtain a data map with order and its total price
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream()
                .collect(Collectors.toMap(
                        order -> order,
                        order -> order.getProducts().stream()
                                .mapToDouble(Product::getPrice)
                                .sum()
                ));
    }

    public Map<Order, Double> exercise13a() {
        // TODO: Obtain a data map with order and its total price (using reduce)
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream()
                .collect(Collectors.toMap(
                        order -> order,
                        order -> order.getProducts().stream()
                                .map(Product::getPrice)
                                .reduce(0.0, Double::sum)
                ));
    }

    public Map<String, List<String>> exercise14() {
        // TODO: Obtain a data map of product name by category
        List<Product> productList = productRepo.findAll();
        return productList.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.toList())
                ));
    }

    public Map<String, Optional<Product>> exercise15() {
        // TODO: Get the most expensive product per category
        List<Product> productList = productRepo.findAll();
        return productList.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.maxBy(Comparator.comparing(Product::getPrice))
                ));
    }

    public Map<String, String> exercise15a() {
        // TODO: Get the most expensive product (by name) per category
        List<Product> productList = productRepo.findAll();
        return productList.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Product::getPrice)),
                                opt -> opt.map(Product::getName).orElse("")
                        )
                ));
    }
}