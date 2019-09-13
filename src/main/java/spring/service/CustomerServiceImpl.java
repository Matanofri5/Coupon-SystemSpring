package spring.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.CouponClientFacade;
import spring.DateUtils;
import spring.exceptions.CouponNotAvailableException;
import spring.models.ClientType;
import spring.models.Company;
import spring.models.Coupon;
import spring.models.Customer;
import spring.models.Income;
import spring.models.IncomeType;
import spring.repository.CouponRepository;
import spring.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService, CouponClientFacade {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private IncomeService incomeService;

	private Customer customer;

	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public Customer purchaseCoupon(long couponId) throws CouponNotAvailableException {

		try {
			if (!couponRepository.existsById(couponId)) {
				throw new CouponNotAvailableException("This coupon doesn't exist, please try another one !");
			}
			
			Coupon coupon = couponRepository.findById((long) couponId).get();
			
			if (coupon.getAmount() <= 0) {
				throw new CouponNotAvailableException("Unable to purache coupon with 0 amount");
			}
//			if (coupon.getEndDate().getTime() <= coupon.getStartDate().getTime()) {
//				throw new CouponNotAvailableException("This coupon is out of stock");
//			}
			
			
			
			couponRepository.save(coupon);
			Customer customer = customerRepository.findById(this.customer.getId()).get();
			customer.getCoupons().add(coupon);
			customerRepository.save(customer);
			coupon.setAmount(coupon.getAmount()-1);
			
			Income income = new Income();
			income.setAmount(coupon.getAmount());
			income.setDate((Date) DateUtils.getCurrentDate());
			income.setDescription(IncomeType.CUSTOMER_PURCHASE);
			income.setName("customer " + customer.getCustomerName());
			incomeService.storeIncome(income);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return customer;
	}
	
	
	
	public List<Coupon> getAllCustomerPurchases(long customer_id) throws Exception{
		Customer customer = customerRepository.getOne(customer_id);
		if (customer!=null) {
			List<Coupon>coupons = customer.getCoupons();
			if (coupons!=null) {
				return coupons;
			}else {
				throw new CouponNotAvailableException("This customer doesn't have any coupons");
			}
			}else {
				throw new Exception("This customer doesn't exist");
			}
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}
}
