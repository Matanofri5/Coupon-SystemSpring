package spring;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import spring.repository.CouponRepository;

@Component
public class ScheduledTask {
	
	@Autowired
	private CouponRepository couponRepository;
	
	public void removeExpiredCoupons(Date date) {
	couponRepository.deleteAll(couponRepository.findByEndDateBefore(date));
}

	@Scheduled(fixedRate=1000 * 60 * 60 * 24)
	public void MyTask() {
		removeExpiredCoupons(new Date(System.currentTimeMillis()));
	}

	}

