package spring;

import org.springframework.beans.factory.annotation.Autowired;

import spring.repository.CompanyRepository;
import spring.repository.CouponRepository;
import spring.repository.CustomerRepository;

public class CouponsThread implements Runnable{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	CouponRepository couponRepository;
	
	private boolean exit = false;
	private boolean running = true;

	@Override
	public void run() {
		
		
	}
	
	
}
