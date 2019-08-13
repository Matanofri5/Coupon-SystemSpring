package spring.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.models.Company;
import spring.models.Coupon;
import spring.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Resource
	private CompanyService companyService;

	@GetMapping("/all")
	public ResponseEntity<List<Company>> findAll(){ 
		ResponseEntity<List<Company>> result = new ResponseEntity<List<Company>>(companyService.findAll(), HttpStatus.OK);
		return result;
	}
	
	@PostMapping("/createCoupon")
	public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon){ 
		Coupon coupon2 = companyService.createCoupon(coupon);

		ResponseEntity<Coupon> result = new ResponseEntity<Coupon>(coupon2, HttpStatus.OK);
		return result;
	}
}
