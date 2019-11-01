package spring.models;

import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COUPON")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @Column(unique=true)
	private long id;

	@Basic(optional = false)
	@Column(nullable = false, name = "title")
	private String title;

	@JsonFormat(pattern = "yyyy-dd-MM")
	@Basic(optional = false)
	@Column(nullable = false, name = "startDate")
	private Date startDate;

	@JsonFormat(pattern = "yyyy-dd-MM")
	@Basic(optional = false)
	@Column(nullable = false, name = "endDate")
	private Date endDate;

	@Basic(optional = false)
	@Column(nullable = false, name = "amount")
	private int amount;

	@Basic(optional = false)
	@Column(nullable = false, name = "message")
	private String message;

	@Basic(optional = false)
	@Column(nullable = false, name = "price")
	private double price;

	@Basic(optional = false)
	@Column(nullable = false, name = "image")
	private String image;

	@Basic(optional = false)
	@Column(nullable = false, name = "type")
	@Enumerated(EnumType.STRING)
	private CouponType type;

}
