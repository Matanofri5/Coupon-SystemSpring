package spring.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="COUPON")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon{

//	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true)
	private long id;
	
	@Basic(optional = false)
	@Column(nullable = false, name="title")
	private String title;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Basic(optional = false)
	@Column(nullable = false, name="startDate")
	private Date startDate;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Basic(optional = false)
	@Column(nullable = false, name="endDate")
	private Date endDate;
	
	@Basic(optional = false)
	@Column(nullable = false, name="amount")
	private int amount;
	
	@Basic(optional = false)
	@Column(nullable = false, name="message")
	private String message;
	
	@Basic(optional = false)
	@Column(nullable = false, name="price")
	private double price;
	
	@Basic(optional = false)
	@Column(nullable = false, name="image")
	private String image;
	
	@Basic(optional = false)
	@Column(nullable = false, name="type")
	@Enumerated(EnumType.STRING)
	private CouponType type;
	
	
//	public Coupon(long id, String title, long startDate, long endDate, int amount, CouponType type, String message,
//			double price, String image) {
//		this.id = id;
//		this.title = title;
//		setStartDate(startDate);
//		setEndDate(endDate);
//		this.amount = amount;
//		this.type = type;
//		this.message = message;
//		this.price = price;
//		this.image = image;
//	}
//	
//	public void setStartDate(long timestamp) {
//		this.startDate = new Date(timestamp);
//	}
//	public void setEndDate(long timestamp) {
//		this.endDate = new Date(timestamp);
//	}

	
}
