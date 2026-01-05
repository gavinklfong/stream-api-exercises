package space.gavinklfong.demo.streamapi.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import space.gavinklfong.demo.streamapi.models.Order;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {

	List<Order> findAll();

	@Query("Select distinct o FROM Order o JOIN FETCH o.products p WHERE p.category ='Baby'")
	List<Order> exercise2();

	@Query("SELECT o FROM Order o ORDER BY o.orderDate DESC")
	List<Order> exercise6();

	@Query("select sum(p.price) FROM Order o JOIN o.products p WHERE o.orderDate >= '2021-02-01' AND o.orderDate < '2021-03-01'")
	Double exercise8();

	@Query("select avg(p.price) FROM Order o JOIN o.products p WHERE o.orderDate = '2021-03-15'")
	Double exercise9();

	@Query("SELECT o.id, COUNT(p) FROM Order o JOIN o.products p GROUP BY o.id")
	List<Object[]> exercise11();

	@Query("SELECT o.customer, o FROM Order o JOIN o.customer")
	List<Object[]> exercise12();

	@Query("SELECT o.customer.id, o.id FROM Order o")
	List<Object[]> exercise12a();

	@Query("SELECT o, SUM(p.price) FROM Order o JOIN o.products p GROUP BY o")
	List<Object[]> exercise13();
}
