package spring.service;

import java.util.List;

import spring.exceptions.CouponNotAvailableException;
import spring.models.Customer;

public interface CustomerService {

	void setCustomer(Customer customer);

	Customer purchaseCoupon(long couponId) throws CouponNotAvailableException;



}