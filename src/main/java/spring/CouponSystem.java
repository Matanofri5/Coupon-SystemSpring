//package spring;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import spring.models.ClientType;
//import spring.models.Company;
//import spring.repository.CompanyRepository;
//import spring.repository.CustomerRepository;
//import spring.service.CompanyServiceImpl;
//
//public class CouponSystem {
//
//	@Autowired
//	private ApplicationContext context;
//	
//	@Autowired
//	private CompanyRepository companyRepo;
//	
//	@Autowired
//	private CustomerRepository customerRepo;
//	
//	
//	
//	
//	
//	
//	
//	
//	public CouponClientFacade login (String name, String password, ClientType clientType) {
//		switch (clientType) {
//		case ADMIN:
//			if (name.equals("admin")&&password.equals("1234")) {
//				
//			}
//		case COMPANY:
//			Company company = companyRepo.findCompanyByCompanyNameAndPassword(name, password);
//			if (company!=null) {
//				CompanyServiceImpl comp = context.getBean(CompanyServiceImpl.class);
//				
//			}
//		default:
//			break;
//		}
//	}
//}
