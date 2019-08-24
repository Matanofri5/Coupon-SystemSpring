package spring.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String title;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private Date startDate;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private Date endDate;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private int amount;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String message;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private double price;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String image;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private CouponType type;
	
	
	@ManyToOne
//	@JsonIgnore
	@Valid
	private Company company;
	@ManyToMany(mappedBy = "coupons")
	@Valid
	private List<Customer> customers;
	
	
}
