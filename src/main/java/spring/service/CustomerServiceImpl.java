package spring.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.CouponClientFacade;
import spring.exceptions.CouponNotAvailableException;
import spring.models.ClientType;
import spring.models.Coupon;
import spring.models.Customer;
import spring.repository.CouponRepository;
import spring.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService, CouponClientFacade {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CouponRepository couponRepository;

	private Customer customer;

	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	 public void purchaseCoupon (long couponId) throws CouponNotAvailableException{
		 
	 try {
		 if (!couponRepository.existsById(couponId)) {
			 throw new CouponNotAvailableException("This coupon doesn't exist, please try another one !");
	 }
		 Coupon coupon = couponRepository.findById((long)couponId).get();
		 	if (coupon.getAmount()<=0) {
				throw new CouponNotAvailableException("Unable to purache coupon with 0 amount");
			}
//		 	if (coupon.getEndDate()) {
//				
//			}
	 } catch (Exception e) {
	 // TODO: handle exception
	 }
	
	
	 }

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}
}
