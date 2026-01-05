package space.gavinklfong.demo.streamapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import space.gavinklfong.demo.streamapi.models.Customer;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.CustomerRepo;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;

import java.util.*;

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
        return new ArrayList<>();
    }

    public List<Product> exercise1a() {
        // TODO: Obtain a list of product with category = "Books" and 
        //  price > 100 (using Predicate chaining for filter)
        return new ArrayList<>();
    }

    public List<Product> exercise1b() {
        // TODO: Obtain a list of product with category = "Books" and price > 100 (using BiPredicate for filter)
        return new ArrayList<>();
    }

    public List<Order> exercise2() {
        // TODO: Obtain a list of order with product category = "Baby"
        return new ArrayList<>();
    }

    public List<Product> exercise3() {
        // TODO: Obtain a list of product with category = "Toys" and then apply 10% discount
        return new ArrayList<>();
    }

    public List<Product> exercise4() {
        // TODO: Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
        return new ArrayList<>();
    }

    public List<Product> exercise5() {
        // TODO: Get the 3 cheapest products of "Books" category
        return new ArrayList<>();
    }

    public List<Order> exercise6() {
        // TODO: Get the 3 most recent placed order
        return new ArrayList<>();
    }

    public List<Product> exercise7() {
        // TODO: Get a list of products which was ordered on 15-Mar-2021
        return new ArrayList<>();
    }

    public Double exercise8() {
        // TODO: Calculate the total lump of all orders placed in Feb 2021
        return 0.0;
    }

    public Double exercise8a() {
        // TODO: Calculate the total lump of all orders placed in Feb 2021 (using reduce with BiFunction)
        return 0.0;
    }

    public Double exercise9() {
        // TODO: Calculate the average price of all orders placed on 15-Mar-2021
        return 0.0;
    }

    public DoubleSummaryStatistics exercise10() {
        // TODO: Obtain statistics summary of all products belong to "Books" category
        return new DoubleSummaryStatistics();
    }

    public Map<Long, Integer> exercise11() {
        // TODO: Obtain a mapping of order id and the order's product count
        return new HashMap<>();
    }

    public Map<Customer, List<Order>> exercise12() {
        // TODO: Obtain a data map of customer and list of orders
        return new HashMap<>();
    }

    public Map<Long, List<Long>> exercise12a() {
        // TODO: Obtain a data map of customer_id and list of order_id(s)
        return new HashMap<>();
    }

    public Map<Order, Double> exercise13() {
        // TODO: Obtain a data map with order and its total price
        return new HashMap<>();
    }

    public Map<Order, Double> exercise13a() {
        // TODO: Obtain a data map with order and its total price (using reduce)
        return new HashMap<>();
    }

    public Map<String, List<String>> exercise14() {
        // TODO: Obtain a data map of product name by category
        return new HashMap<>();
    }

    public Map<String, Optional<Product>> exercise15() {
        // TODO: Get the most expensive product per category
        return new HashMap<>();
    }

    public Map<String, String> exercise15a() {
        // TODO: Get the most expensive product (by name) per category
        return new HashMap<>();
    }
}