package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.models.Company;
import spring.models.Coupon;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

	void save(Coupon coupon);

}
