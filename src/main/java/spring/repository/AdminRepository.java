package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.models.Company;
import spring.models.Coupon;
import spring.models.Customer;

@Repository
public interface AdminRepository extends JpaRepository<Coupon, Long>{

	void save(Company company);

	void save(Customer customer);


}
