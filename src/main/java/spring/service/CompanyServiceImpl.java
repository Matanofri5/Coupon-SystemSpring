package spring.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
	private IncomeRepository incomeRepository;

	private Customer customer;
	
	private Company company;

	@Override
	public void setCompany(Company company) {
		this.company = company;
	}

	// @Override
	// public List<Company> findAll(){
	// return companyRepository.findAll();
	// }

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
	public void deleteCoupon(long couponId) throws CouponNotAvailableException {
		if (!couponRepository.existsById(couponId)) {
			throw new CouponNotAvailableException("This coupon id doesn't exist in DataBase");
		}
//		couponRepository.deleteById(couponId);
//		long i = 0;
//		Company company = companyRepository.getOne(this.company.getId());
//		List<Coupon> coupons = new ArrayList<Coupon>(company.getCoupons());
//		couponRepository.deleteById(couponId);
//
//		for (i=0; i<coupons.size(); i++) {		
//			couponRepository.delete(coupons.get((int) i));
//		}
//		Customer customer = customerRepository.getOne(this.customer.getId());
//		List<Coupon>coupons2 = new ArrayList<Coupon>(customer.getCoupons());
//		couponRepository.deleteById(couponId);
//
//		for (i=0; i<coupons2.size(); i++) {
//			couponRepository.delete(coupons2.get((int) i));		
//		}
		
		Coupon coupon = couponRepository.getOne(couponId);
		if (coupon!=null) {
			couponRepository.deleteById(couponId);
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
	public List<Coupon> couponByCouponType(CouponType couponType) throws Exception {
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
	public List<Coupon> couponByPrice(double price) throws Exception {
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
	public List<Coupon> couponByDate(Date endDate) throws Exception {
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
