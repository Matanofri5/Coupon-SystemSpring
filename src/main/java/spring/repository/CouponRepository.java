package spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.models.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{

	Coupon findByTitle(String title);
	
//	@Query("SELECT c FROM coupon AS c WHERE c.endDate <= startDate")
//	List<Coupon> findExpiredCoupons;

}
