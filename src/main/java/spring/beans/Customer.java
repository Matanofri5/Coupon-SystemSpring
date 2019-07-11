package spring.beans;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue
	private long id;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String customerName;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String password;
	
}
