package spring.service;

import java.util.List;

import spring.exceptions.CouponNotAvailableException;
import spring.models.Coupon;
import spring.models.CouponType;
import spring.models.Customer;

public interface CustomerService {

	void setCustomer(Customer customer);

	Customer purchaseCoupon(long couponId) throws CouponNotAvailableException;

	List<Coupon> getCouponsByCouponType(CouponType couponType) throws Exception;

	List<Coupon> getCouponsByPrice(double price) throws Exception;

	List<Coupon> getAllCustomerCoupons(long customer_id) throws Exception;



}