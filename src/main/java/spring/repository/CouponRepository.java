package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.models.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

}
