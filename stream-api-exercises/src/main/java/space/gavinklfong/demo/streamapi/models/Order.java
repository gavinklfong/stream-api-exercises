package space.gavinklfong.demo.streamapi.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter @Getter
	private Long id;
	
	@Setter @Getter
	private LocalDate orderDate;

	@Setter @Getter
	private LocalDate deliveryDate;
	
	@Setter @Getter
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@Setter @Getter
	private Customer customer;
	
	@ManyToMany
	@JoinTable(
			name = "order_product_relationship",
			joinColumns = { @JoinColumn(name = "order_id") },
			inverseJoinColumns = { @JoinColumn(name = "product_id") }
	)
	@Setter @Getter
	Set<Product> products;
	
	@Override
	public String toString() {
		return String.format("id=$1%d, orderDate=%2$tF, deliveryDate=%3$tF, status=%4$s", id, orderDate, deliveryDate, status);
	}
	
}
