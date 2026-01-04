package space.gavinklfong.demo.streamapi.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import space.gavinklfong.demo.streamapi.models.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

	List<Product> findAll();

	@Query("SELECT p FROM Product p WHERE p.category = 'Books' and p.price > 100")
	List<Product> exercise1();

	@Query("SELECT DISTINCT p FROM Product p JOIN p.orders o WHERE o.customer.tier = 2 AND o.orderDate >= '2021-02-01' AND o.orderDate < '2021-04-01'")
	List<Product> exercise4();

	@Query("SELECT p FROM Product p WHERE p.category='Books' ORDER BY p.price ASC")
	List<Product> exercise5();

	@Query("SELECT DISTINCT p FROM Product p JOIN FETCH p.orders o WHERE o.orderDate ='2021-03-15'")
	List<Product> exercise7();

	@Query("SELECT count(p) FROM Product p where p.category = 'Books'")
	Integer exercise10Count();

	@Query("SELECT sum(p.price) FROM Product p where p.category = 'Books'")
	Double exercise10Sum();

	@Query("SELECT min(p.price) FROM Product p where p.category = 'Books'")
	Double exercise10Min();

	@Query("SELECT max(p.price) FROM Product p where p.category = 'Books'")
	Double exercise10Max();

	@Query("SELECT p.category, p.name FROM Product p ORDER BY p.category")
	List<Object[]> exercise14();

	@Query("SELECT p.category, p FROM Product p")
	List<Object[]> exercise15();
}
