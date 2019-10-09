package spring.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.CouponClientFacade;
import spring.DateUtils;
import spring.exceptions.CouponNotAvailableException;
import spring.models.ClientType;
import spring.models.Company;
import spring.models.Coupon;
import spring.models.CouponType;
import spring.models.Customer;
import spring.models.Income;
import spring.models.IncomeType;
import spring.repository.CompanyRepository;
import spring.repository.CouponRepository;
import spring.repository.CustomerRepository;
import spring.repository.IncomeRepository;

@Service
public class CompanyServiceImpl implements CompanyService, CouponClientFacade {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@Autowired
	private IncomeRepository incomeRepository;

	private Customer customer;

	private Company company;

	@Override
	public void setCompany(Company company) {
		this.company = company;
	}


	@Override
	public boolean checkIfTitleAlreadyExists(String title) {
		if (couponRepository.findByTitle(title) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Coupon createCoupon(Coupon coupon) throws Exception {
		if (checkIfTitleAlreadyExists(coupon.getTitle()) == false) {
			couponRepository.save(coupon);
			Company comp = companyRepository.findById(this.company.getId()).get();
			comp.getCoupons().add(coupon);
			companyRepository.save(comp);
			Income income = new Income();
			income.setClientId(this.company.getId());
			income.setAmount(100.0);
			income.setDescription(IncomeType.COMPANY_NEW_COUPON);
			income.setDate((Date) DateUtils.getCurrentDate());
			income.setName("Company " + company.getCompanyName());
			incomeRepository.save(income);
			
		} else {
			throw new Exception("The title " + coupon.getTitle() + " already exist, please try another title");
		}
		return coupon;
	}

	@Override
	public void updateCoupon(Coupon coupon, Date endDate, double price) {
		coupon.setEndDate(endDate);
		coupon.setPrice(price);
		couponRepository.save(coupon);
		Income income = new Income();
		income.setClientId(this.company.getId());
		income.setAmount(10.0);
		income.setDescription(IncomeType.COMPANY_UPDATE_COUPON);
		income.setDate((Date) DateUtils.getCurrentDate());
		income.setName("Company " + company.getCompanyName());
		incomeRepository.save(income);
	}
	
	
	
	@Override
	public void deleteCoupon(long coupon_id) throws Exception {
		Coupon coupon2 = couponRepository.getOne(coupon_id);
		if (coupon2!=null) {
			couponRepository.delete(coupon2);
		}else {
			throw new CouponNotAvailableException("This coupon id doesn't exist in DataBase");

		}

	}

	@Override
	public Company companyById(long id) {
		return companyRepository.findById(id).get();
	}

	@Override
	public List<Coupon> getAllCompanyCoupons(long company_id) throws Exception {
		Company company = companyRepository.getOne(company_id);
		if (company != null) {
			List<Coupon> coupons = company.getCoupons();
			if (coupons != null) {
				return coupons;
			} else {
				throw new CouponNotAvailableException("This company doesn't have any coupons");
			}
		} else {
			throw new Exception("This company doesn't exist");
		}
	}

	@Override
	public List<Coupon> getCouponsByCouponType(CouponType couponType) throws Exception {
		List<Coupon> allCompanycoupons = getAllCompanyCoupons(this.company.getId());
		List<Coupon> couponsByType = couponRepository.findByType(couponType);
		try {
			for (Coupon coupon : allCompanycoupons) {
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
		List<Coupon> allCompanyCoupons = getAllCompanyCoupons(this.company.getId());
		List<Coupon> couponsByPrice = couponRepository.findByPriceLessThan(price);
		try {
			for (Coupon coupon : allCompanyCoupons) {
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
	public List<Coupon> getCouponsByEndDate(Date endDate) throws Exception {
		List<Coupon> allCompanyCoupons = getAllCompanyCoupons(this.company.getId());
		List<Coupon> couponsByDate = couponRepository.findByEndDateBefore(endDate);
		try {
			for (Coupon coupon : allCompanyCoupons) {
				if (coupon.getEndDate().equals(endDate) || coupon.getEndDate().before(endDate)) {
					couponsByDate.add(coupon);
				}
			}
		} catch (Exception e) {
			System.out.println("Failed to get all coupons by date " + e.getMessage());
		}
		return couponsByDate;
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

}
