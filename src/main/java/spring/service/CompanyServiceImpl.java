package spring.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.models.Company;
import spring.models.Coupon;
import spring.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	/* (non-Javadoc)
	 * @see spring.service.CompanyService#findAll()
	 */
	@Override
	public List<Company> findAll(){
		return companyRepository.findAll();
	}
	
	@Override
	public Coupon createCoupon(Coupon coupon) {
		companyRepository.save(coupon);
		return coupon;
	}


}
