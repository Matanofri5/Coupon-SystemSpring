package spring.service;

import java.sql.Date;
import java.util.List;
import spring.models.Company;
import spring.models.Coupon;

public interface CompanyService {

//	List<Company> findAll();

	Coupon createCoupon(Coupon coupon) throws Exception;

	boolean checkIfTitleAlreadyExists(String title);

	void updateCoupon(Coupon coupon, Date endDate, double price);

	Company companyById(long id);


}