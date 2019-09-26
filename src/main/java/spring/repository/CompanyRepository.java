package spring.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.models.Company;
import spring.models.Coupon;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

	Company findByCompanyName(String companyName);
	
	Company findCompanyByCompanyNameAndPassword(String companyName, String password);
	
//	@Query("SELECT company FROM COMPANY as company join company.coupons as c.id=:couponId")
//	List<Company> findCompanyByCoupons(long couponId);
	
	// @Query("select c from Coupon c where c.endDate = :endDate")
	// List<Coupon> findCompanyCouponsByDate(Date endDate);
	
	
}
