package spring.service;

import java.util.List;

import spring.models.Company;
import spring.models.Customer;

public interface AdminService {

	Company createCompany(Company company) throws Exception;

	void deleteCompany(long id);

	List<Company> allCompanies();

	Company companyById(long id);

	Customer createCustomer(Customer customer);

	void deleteCustomer(long id);

	boolean checkIfCompanyNameAlreadyExists(String companyName);

}
