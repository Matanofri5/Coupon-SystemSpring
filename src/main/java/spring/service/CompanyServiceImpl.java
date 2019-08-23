package spring.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.models.Company;
import spring.models.Coupon;
import spring.repository.CompanyRepository;
import spring.repository.CouponRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Override
	public List<Company> findAll(){
		return companyRepository.findAll();
	}
	
	@Override
	public Coupon createCoupon(Coupon coupon) {
		couponRepository.save(coupon);
		return coupon;
	}


}
