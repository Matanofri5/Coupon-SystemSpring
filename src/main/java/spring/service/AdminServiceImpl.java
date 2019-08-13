package spring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import spring.models.Company;
import spring.models.Coupon;
import spring.models.Customer;
import spring.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminRepository adminRepository;
	

	@Override
	public Company createCompany (Company company) {
		adminRepository.save(company);
		return company;
	}
	
	
	@Override
	public List<Coupon> findAll(){
		return adminRepository.findAll();
	}

	@Override
	public Coupon createCoupon(Coupon coupon) {
		adminRepository.save(coupon);
		return coupon;
	}
	@Override
	public Customer createCustomer (Customer customer) {
		adminRepository.save(customer);
		return customer;
	}

	@Override
	public void removeCompany (Company company) {
		adminRepository.deleteById(company.getId());
	}
}
