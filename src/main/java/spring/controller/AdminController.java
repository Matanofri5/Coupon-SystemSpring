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
import spring.models.Coupon;
import spring.service.CouponService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Resource
	private CouponService couponService;
	
	@GetMapping("/getAllCoupons")
	public ResponseEntity<List<Coupon>> findAll(){
		ResponseEntity<List<Coupon>> result = new ResponseEntity<List<Coupon>>(couponService.findAll(), HttpStatus.OK);
		return result;
	}
	
//	@GetMapping("/getCoupon")
//	public ResponseEntity<Coupon> findById(){
//		ResponseEntity<Coupon> result = new ResponseEntity<Coupon>(couponService.findById(), HttpStatus.OK);
//		return result;
//	}
	
	@PostMapping("/createCoupon")
	public ResponseEntity<Coupon> createCoupon (@RequestBody Coupon coupon){
		Coupon coupon2 = couponService.createCoupon(coupon);
		System.out.println(coupon2);
		ResponseEntity<Coupon> result = new ResponseEntity<Coupon>(coupon2,HttpStatus.OK);
		return result;
	}
	
}
