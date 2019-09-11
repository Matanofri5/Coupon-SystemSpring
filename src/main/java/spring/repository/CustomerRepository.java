package spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import spring.models.Company;
import spring.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Customer findByCustomerName(String customerName);
	
	Customer findByCustomerNameAndPassword(String customerName, String password);

//	@Query("SELECT customer FROM CUSTOMER as customer join customer.coupons as c.id=:couponId")
//	List<Customer> findCustomerByCoupons(long couponId);
}
