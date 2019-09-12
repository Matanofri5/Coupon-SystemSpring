package spring.service;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.CouponClientFacade;
import spring.DateUtils;
import spring.exceptions.CouponNotAvailableException;
import spring.models.ClientType;
import spring.models.Company;
import spring.models.Coupon;
import spring.models.Income;
import spring.models.IncomeType;
import spring.repository.CompanyRepository;
import spring.repository.CouponRepository;
import spring.repository.IncomeRepository;

@Service
public class CompanyServiceImpl implements CompanyService, CouponClientFacade {


	private long company_id;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private IncomeRepository incomeRepository;
	
	@Autowired
	private IncomeService incomeService;
	
	private Company company;
	@Override
	public void setCompany(Company company) {
		this.company = company;
	}
	
//	@Override
//	public List<Company> findAll(){
//		return companyRepository.findAll();
//	}
	
	@Override
	public boolean checkIfTitleAlreadyExists(String title) {
		if (couponRepository.findByTitle(title) != null) {
			return true;
		}
		return false;
	}
	@Override
	public Coupon createCoupon(Coupon coupon) throws Exception {
		if (checkIfTitleAlreadyExists(coupon.getTitle())== false) {
			couponRepository.save(coupon);
			Company comp = companyRepository.findById(this.company.getId()).get();
			comp.getCoupons().add(coupon);
			companyRepository.save(comp);
			Income income = new Income();
			income.setAmount(100.0);
			income.setDescription(IncomeType.COMPANY_NEW_COUPON);
			income.setDate((Date) DateUtils.getCurrentDate());
			income.setName("Company " + company.getCompanyName() + " buy coupon "+ coupon.getTitle());
			incomeService.storeIncome(income);
		}else {
			throw new Exception("The title " + coupon.getTitle() +" already exist, please try another title");
		}
		return coupon;
	}
	
	@Override 
	public void updateCoupon(Coupon coupon, Date endDate, double price) {
		coupon.setEndDate(endDate);
		coupon.setPrice(price);
		couponRepository.save(coupon);
		Income income = new Income();
		income.setAmount(10.0);
		income.setDescription(IncomeType.COMPANY_UPDATE_COUPON);
		income.setDate((Date) DateUtils.getCurrentDate());
		income.setName("Company " + company.getCompanyName() + " buy coupon "+ coupon.getTitle());
		incomeService.storeIncome(income);
	}
	
	
	@Override
	public void deleteCoupon(long id) {
		couponRepository.deleteById(id);
	}
	
	@Override
	public Company companyById(long id) {
		return companyRepository.findById(id).get();
	}
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Coupon> getAllCompanyCoupons(long company_id) throws Exception{
		Company company = companyRepository.getOne(company_id);
		if (company!=null) {
		List<Coupon> coupons=company.getCoupons();
		if (coupons!=null) {
			return coupons;
		}else {
			throw new CouponNotAvailableException("This company doesn't have any coupons");
		}
		}else {
			throw new Exception("This company doesn't exist");
		}
	}
	
	


}
