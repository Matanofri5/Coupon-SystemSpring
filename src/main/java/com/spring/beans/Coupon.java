package com.spring.beans;

import java.sql.Date;

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
public class Coupon {

	@Id
	@GeneratedValue
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
	
//	@Basic(optional = false)
//	@Column(nullable = false)
//	private CouponType type;
	
	
}
