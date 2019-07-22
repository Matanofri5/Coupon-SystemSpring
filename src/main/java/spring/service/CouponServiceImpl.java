package spring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import spring.models.Coupon;
import spring.repository.CouponRepository;

@Service
public class CouponServiceImpl implements CouponService {

	@Resource
	private CouponRepository couponRepository;
	
	/* (non-Javadoc)
	 * @see spring.service.CouponService#findAll()
	 */
	@Override
	public List<Coupon> findAll(){
		return couponRepository.findAll();
	}

	@Override
	public Coupon createCoupon(Coupon coupon) {
		couponRepository.save(coupon);
		return coupon;
	}

}
