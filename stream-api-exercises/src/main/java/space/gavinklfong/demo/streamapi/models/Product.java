package space.gavinklfong.demo.streamapi.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Builder
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter @Getter
	private Long id;

	@Setter @Getter
	private String name;

	@Setter @Getter
	private String category;
	
	@Setter @Getter
	@With
	private Double price;
	
	@ManyToMany(mappedBy = "products")
	@Setter @Getter
	private Set<Order> orders;
	
	@Override
	public String toString() {
		return String.format("id=$1%d, name=%2$s, category=%3$s, price=%4$f", id, name, category, price);
	}
}
