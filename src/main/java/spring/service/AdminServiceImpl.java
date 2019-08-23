package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.models.Company;
import spring.models.Customer;
import spring.repository.CompanyRepository;
import spring.repository.CouponRepository;
import spring.repository.CustomerRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CouponRepository adminRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public boolean checkIfCompanyNameAlreadyExists(String companyName) {
		if (companyRepository.findByCompanyName(companyName) != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public Company createCompany(Company company) throws Exception {
		if (checkIfCompanyNameAlreadyExists(company.getCompanyName())==false) {
			companyRepository.save(company);
			return company;
		}else {
			throw new Exception("The company " + company.getCompanyName() +" already exist, please try another name");
		}
    }
	
	@Override
	public void deleteCompany(long id) {
		companyRepository.deleteById(id);
	}
	
	@Override
	public List<Company> allCompanies(){
		return companyRepository.findAll();
	}
	
	@Override
	public Company companyById(long id){
		return companyRepository.findById(id).get();
	}
	
	@Override
	public Customer createCustomer(Customer customer) {
		customerRepository.save(customer);
		return customer;
	}
	
	@Override
	public void deleteCustomer(long id) {
		customerRepository.deleteById(id);
	}
	
	public List<Customer> allCustomers(){
		return customerRepository.findAll();
	}
	
	public Customer customerById(long id) {
		return customerRepository.findById(id).get();
	}
}
