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
public class CustomerCoupon {

	@Id
	@GeneratedValue
	@Basic(optional = false)
	@Column(nullable = false)
	private long customerId;
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	@Column(nullable = false)
	private long couponId;
}
