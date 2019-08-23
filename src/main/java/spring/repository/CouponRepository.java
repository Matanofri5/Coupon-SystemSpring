package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.models.Company;
import spring.models.Coupon;
import spring.models.Customer;

public interface CouponRepository extends JpaRepository<Coupon, Long>{


}
