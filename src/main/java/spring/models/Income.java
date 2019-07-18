package spring.models;

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
public class Income {

	@Id
	@GeneratedValue
	private long id;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String name;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private Date date;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private IncomeType incomeType;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private double amount;
	
	
}
