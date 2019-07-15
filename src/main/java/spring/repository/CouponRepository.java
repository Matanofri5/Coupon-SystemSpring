package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.models.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{

}
