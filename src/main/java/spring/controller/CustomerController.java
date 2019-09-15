package spring.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.Session;
import spring.models.Coupon;
import spring.models.CouponType;
import spring.models.Customer;
import spring.service.CompanyServiceImpl;
import spring.service.CustomerService;
import spring.service.CustomerServiceImpl;
import spring.service.IncomeService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	

	@Autowired
	private Map<String, Session> tokens;

	private Session exists(String token) {
		return LoginController.tokens.get(token);
	}

//	 @PostMapping("/purchaseCoupon/{token}")
//	 public ResponseEntity<Customer> purchaseCoupon (@PathVariable long couponId,
//	 @PathVariableString token) throws Exception{
//	 Customer customer = customerService.purchaseCoupon(couponId);
//	 ResponseEntity<Customer> result = new
//	 ResponseEntity<Customer>(customer,HttpStatus.OK);
//	 return result;
//	 }
	
	@PostMapping("/purchaseCoupon/{couponId}/{token}")
	public ResponseEntity<String> purchaseCoupon(@PathVariable long couponId, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				if (((CustomerServiceImpl) session.getFacade()).purchaseCoupon(couponId) != null) {
				}return new ResponseEntity<>("Customer purchaed coupon :  " + couponId, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage() + e.getStackTrace(), HttpStatus.UNAUTHORIZED);
			}
		}
		return null;
	}

	@GetMapping("/getAllCustomerCoupons/{customer_id}/{token}")
	public List<Coupon> getAllCustomerCoupons(@PathVariable long customer_id, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((CustomerServiceImpl) session.getFacade()).getAllCustomerPurchases(customer_id);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	@GetMapping("/getCustomerByCouponType/{couponType}/{token}")
	public List<Coupon> getCustomerByCouponType(@PathVariable CouponType couponType, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((CustomerServiceImpl) session.getFacade()).couponByType(couponType);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	@GetMapping("/getCustomerByPrice/{price}/{token}")
	public List<Coupon> getCustomerByPrice(@PathVariable double price, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((CustomerServiceImpl) session.getFacade()).couponByPrice(price);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
}
