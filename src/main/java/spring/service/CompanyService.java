package spring.service;

import java.util.List;
import spring.models.Company;
import spring.models.Coupon;

public interface CompanyService {

	List<Company> findAll();

	Coupon createCoupon(Coupon coupon);


}