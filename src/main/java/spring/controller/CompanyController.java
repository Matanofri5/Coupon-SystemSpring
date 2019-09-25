package spring.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.catalina.util.SessionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jayway.jsonpath.internal.Path;

import spring.Session;
import spring.models.Company;
import spring.models.Coupon;
import spring.models.CouponType;
import spring.models.Income;
import spring.repository.CouponRepository;
import spring.service.AdminServiceImpl;
import spring.service.CompanyService;
import spring.service.CompanyServiceImpl;
import spring.service.CustomerServiceImpl;
import spring.service.IncomeService;
import spring.service.IncomeServiceImpl;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private IncomeService incomeService;

	@Autowired
	private Map<String, Session> tokens;

	private Session exists(String token) {
		return LoginController.tokens.get(token);
	}

	@PostMapping("/createCoupon/{token}")
	public ResponseEntity<String> createCoupon(@RequestBody Coupon coupon, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				((CompanyServiceImpl) session.getFacade()).createCoupon(coupon);
				return new ResponseEntity<>("coupon created  " + coupon, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage() + e.getStackTrace(), HttpStatus.UNAUTHORIZED);
			}
		}
		return null;
	}

	@PostMapping("/updateCoupon/{token}")
	public ResponseEntity<Coupon> updateCoupon(@PathVariable String token, @RequestParam long id,
			@RequestParam Date endDate, @RequestParam double price) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Coupon coupon = null;
				coupon = couponRepository.findById(id).get();
				if (coupon != null) {
					((CompanyServiceImpl) session.getFacade()).updateCoupon(coupon, endDate, price);
					ResponseEntity<Coupon> result = new ResponseEntity<>(coupon, HttpStatus.OK);
					return result;
				}
			} catch (Exception e) {
				System.out.println("Cannot update coupon");
			}
		}
		return null;
	}

	@DeleteMapping("/deleteCoupon/{coupon_id}/{token}")
	public void deleteCoupon(@PathVariable long coupon_id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Coupon coupon = null;
				coupon = couponRepository.findById(coupon_id).get();
				if (coupon != null) {
					((CompanyServiceImpl) session.getFacade()).deleteCoupon(coupon_id);
				}
			} catch (Exception e) {
				System.out.println("Failed to delete coupon " + coupon_id + e.getMessage());
			}
		}
	}

	@GetMapping("/companyById/{id}/{token}")
	public Company companyById(@PathVariable long id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((CompanyServiceImpl) session.getFacade()).companyById(id);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@GetMapping("/getAllCompanyCoupons/{company_id}/{token}")
	public List<Coupon> getAllCompanyCoupons(@PathVariable long company_id, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((CompanyServiceImpl) session.getFacade()).getAllCompanyCoupons(company_id);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@GetMapping("/getCouponsByCouponType/{couponType}/{token}")
	public List<Coupon> getCouponsByCouponType(@PathVariable CouponType couponType, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((CompanyServiceImpl) session.getFacade()).getCouponsByCouponType(couponType);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	
	@GetMapping("/getCouponsByPrice/{price}/{token}")
	public List<Coupon> getCouponsByPrice(@PathVariable double price, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((CompanyServiceImpl) session.getFacade()).getCouponsByPrice(price);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	@GetMapping(value="/getCouponsByEndDate/{endDate}/{token}")
//	@RequestMapping(value = "/getCompanyByEndDate/{endDate}/{token}", method =RequestMethod.GET, consumes="application/json")
	public List<Coupon> getCouponsByEndDate(@PathVariable Date endDate, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((CompanyServiceImpl) session.getFacade()).getCouponsByEndDate(endDate);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	
	
	
	
	@GetMapping("/viewIncomeByCompanyId/{companyId}/{token}")
	public List<Income> viewIncomeByCompanyId(@PathVariable long companyId, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return incomeService.viewIncomeByCompany(companyId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	
}
