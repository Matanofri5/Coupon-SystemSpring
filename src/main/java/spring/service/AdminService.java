package spring.service;

import java.util.List;

import spring.models.Company;
import spring.models.Coupon;
import spring.models.Customer;

public interface AdminService {

	List<Coupon> findAll();
	
	Coupon createCoupon (Coupon coupon);

	Company createCompany(Company company);

	Customer createCustomer(Customer customer);

	void removeCompany(Company company);


}