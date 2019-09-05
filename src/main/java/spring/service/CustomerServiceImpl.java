package spring.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.CouponClientFacade;
import spring.exceptions.CouponNotAvailableException;
import spring.models.ClientType;
import spring.models.Coupon;
import spring.repository.CouponRepository;
import spring.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService, CouponClientFacade {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	
	public void purchaseCoupon (long couponId) throws CouponNotAvailableException {
		Optional<Coupon> coupon = couponRepository.findById(couponId);
		
		if (coupon==null) {
			throw new CouponNotAvailableException("This coupon doesn't exists, please thy another one");
		}
		
		
	}


	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}
}
