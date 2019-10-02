package spring.service;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.CouponClientFacade;
import spring.DateUtils;
import spring.exceptions.CouponNotAvailableException;
import spring.models.ClientType;
import spring.models.Coupon;
import spring.models.CouponType;
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
				throw new CouponNotAvailableException("This coupon is out of stock !!");
			}

			if (coupon.getEndDate().getTime() <= coupon.getStartDate().getTime()) {
				throw new CouponNotAvailableException("This coupon has been expired");
			}
			
			List<Coupon> coupons = getAllCustomerCoupons(this.customer.getId());
			Iterator<Coupon> iterator = coupons.iterator();
			while(iterator.hasNext()) {
				Coupon current = iterator.next();
				if (current.getId()==couponId) {
					throw new CouponNotAvailableException("This coupon cannot be purchased again");
				}
			}

			couponRepository.save(coupon);
			Customer customer = customerRepository.findById(this.customer.getId()).get();
			customer.getCoupons().add(coupon);
			customerRepository.save(customer);
			coupon.setAmount(coupon.getAmount() - 1);

			Income income = new Income();
			income.setClientId(this.customer.getId());
			income.setAmount(coupon.getPrice());
			income.setDate((Date) DateUtils.getCurrentDate());
			income.setDescription(IncomeType.CUSTOMER_PURCHASE);
			income.setName("customer " + customer.getCustomerName());
			incomeService.storeIncome(income);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return customer;
	}

	@Override
	public List<Coupon> getAllCustomerCoupons(long customer_id) throws Exception {
		Customer customer = customerRepository.getOne(customer_id);
		if (customer != null) {
			List<Coupon> coupons = customer.getCoupons();
			if (coupons != null) {
				return coupons;
			} else {
				throw new CouponNotAvailableException("This customer doesn't have any coupons");
			}
		} else {
			throw new Exception("This customer doesn't exist");
		}
	}

	@Override
	public List<Coupon> getCouponsByCouponType(CouponType couponType) throws Exception {
		List<Coupon> allCustomercoupons = getAllCustomerCoupons(this.customer.getId());
		List<Coupon> couponsByType = couponRepository.findByType(couponType);
		try {
			for (Coupon coupon : allCustomercoupons) {
				if (coupon.getType().equals(couponsByType)) {
					couponsByType.add(coupon);
				}
			}
		} catch (Exception e) {
			System.out.println("Failed to get all coupons by type " + e.getMessage());
		}
		return couponsByType;
	}

	@Override
	public List<Coupon> getCouponsByPrice(double price) throws Exception {
		List<Coupon> allCustomerCoupons = getAllCustomerCoupons(this.customer.getId());
		List<Coupon> couponsByPrice = couponRepository.findByPriceLessThan(price);
		try {
			for (Coupon coupon : allCustomerCoupons) {
				if (coupon.getPrice() <= price) {
					couponsByPrice.add(coupon);
				}
			}
		} catch (Exception e) {
			System.out.println("Failed to get all coupons by price " + e.getMessage());
		}
		return couponsByPrice;
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}
}
