package spring;

import java.sql.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import spring.exceptions.couponSystemException;
import spring.models.ClientType;
import spring.models.Company;
import spring.models.Customer;
import spring.repository.CompanyRepository;
import spring.repository.CustomerRepository;
import spring.service.AdminService;
import spring.service.AdminServiceImpl;
import spring.service.CompanyService;
import spring.service.CompanyServiceImpl;
import spring.service.CustomerService;
import spring.service.CustomerServiceImpl;

@Service
public class CouponSystem {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private AdminServiceImpl adminFacade;

	@Autowired
	private CompanyRepository companyRepo;

	@Autowired
	private CustomerRepository customerRepo;
	
//	@Autowired
//	private CouponsThread couponsThread;
//
//	 @PostConstruct
//	 public void init() {
//		 couponsThread.startThread();
//	 }
//
//	 @PreDestroy
//	 public void destroy() {
//		 couponsThread.stopThread();
//	 }
	
	@Autowired
	private ScheduledTask scheduledTask;
	
	@PostConstruct
	public void init() {
		scheduledTask.MyTask();
	}

	public CouponClientFacade login(String name, String password, ClientType clientType) throws couponSystemException {
		switch (clientType) {
		case ADMIN:
			if (name.equals("admin") && password.equals("1234")) {
				adminFacade = context.getBean(AdminServiceImpl.class);
				return (CouponClientFacade) adminFacade;
			}else {
				throw new couponSystemException("Admin failed to connect");
			}
		case COMPANY:
			Company comp = companyRepo.findCompanyByCompanyNameAndPassword(name, password);
			if (comp != null) {
				CompanyServiceImpl company = context.getBean(CompanyServiceImpl.class);
				company.setCompany(comp);
				return (CouponClientFacade) company;
			}
		case CUSTOMER:
			Customer cust = customerRepo.findByCustomerNameAndPassword(name, password);
			if (cust != null) {
				CustomerService customer = context.getBean(CustomerServiceImpl.class);
				customer.setCustomer(cust);
				return (CouponClientFacade) customer;
			}
		}
		throw new couponSystemException("Login Falied! Invalid User or Password!");
	}
}
