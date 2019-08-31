package spring.service;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.models.Company;
import spring.models.Coupon;
import spring.repository.CompanyRepository;
import spring.repository.CouponRepository;

@Service
public class CompanyServiceImpl implements CompanyService {


	private long compId;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
//	@Override
//	public List<Company> findAll(){
//		return companyRepository.findAll();
//	}
	
	@Override
	public boolean checkIfTitleAlreadyExists(String title) {
		if (couponRepository.findByTitle(title) != null) {
			return true;
		}
		return false;
	}
	@Override
	public Coupon createCoupon(Coupon coupon) throws Exception {
		if (checkIfTitleAlreadyExists(coupon.getTitle())== false) {
			couponRepository.save(coupon);
//			Company comp = companyRepository.findById(compId).get();
//			comp.getCoupons().add(coupon);
//
//			companyRepository.save(comp);
		}else {
			throw new Exception("The title " + coupon.getTitle() +" already exist, please try another title");
		}
		return coupon;
	}
	
	@Override 
	public void updateCoupon(Coupon coupon, Date endDate, double price) {
		coupon.setEndDate(endDate);
		coupon.setPrice(price);
		couponRepository.save(coupon);
	}
	
	
	@Override
	public void deleteCoupon(long id) {
		couponRepository.deleteById(id);
	}
	
	@Override
	public Company companyById(long id) {
		return companyRepository.findById(id).get();
	}
	
	


}
