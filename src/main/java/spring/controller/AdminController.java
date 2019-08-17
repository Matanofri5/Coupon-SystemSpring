package spring.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.models.Company;
import spring.models.Coupon;
import spring.models.Customer;
import spring.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Resource
	private AdminService adminService;
	
	@GetMapping("/getAllCoupons")
	public ResponseEntity<List<Coupon>> findAll(){
		ResponseEntity<List<Coupon>> result = new ResponseEntity<List<Coupon>>(adminService.findAll(), HttpStatus.OK);
		return result;
	}
	
//	@GetMapping("/getCoupon")
//	public ResponseEntity<Coupon> findById(){
//		ResponseEntity<Coupon> result = new ResponseEntity<Coupon>(couponService.findById(), HttpStatus.OK);
//		return result;
//	}
	
	@PostMapping("/createCompany")
	public ResponseEntity<Company> createCompany (@RequestBody Company company) throws Exception{
		Company company2 = adminService.createCompany(company);
		ResponseEntity<Company> result = new ResponseEntity<Company>(company2,HttpStatus.OK);
		return result;
	}
	
	@PostMapping("/createCustomer")
	public ResponseEntity<Customer> createCustomer (@RequestBody Customer customer){
		Customer customer2 = adminService.createCustomer(customer);
		ResponseEntity<Customer> result = new ResponseEntity<Customer>(customer2,HttpStatus.OK);
		return result;
	}
	
//	@RequestMapping(method = RequestMethod.DELETE, value = "long Id/{companyId}")
//	public ResponseEntity<Company> removeCompany (@PathVariable long id){
//		try {
//			adminService.removeCompany(id);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return null;
//	}
}
