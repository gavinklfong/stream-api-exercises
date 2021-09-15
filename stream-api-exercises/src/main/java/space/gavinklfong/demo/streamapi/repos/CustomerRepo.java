package space.gavinklfong.demo.streamapi.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import space.gavinklfong.demo.streamapi.models.Customer;

import java.util.List;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {

	List<Customer> findAll();
}
