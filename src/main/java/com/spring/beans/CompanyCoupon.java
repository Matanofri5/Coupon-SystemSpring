package com.spring.beans;

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
public class CompanyCoupon {

	@Id
	@GeneratedValue
	@Basic(optional = false)
	@Column(nullable = false)
	private long companyId;
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	@Column(nullable = false)
	private long couponId;
}
