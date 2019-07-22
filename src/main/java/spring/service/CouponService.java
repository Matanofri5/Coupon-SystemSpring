package spring.service;

import java.util.List;

import spring.models.Coupon;

public interface CouponService {

	List<Coupon> findAll();
	
	Coupon createCoupon (Coupon coupon);

}