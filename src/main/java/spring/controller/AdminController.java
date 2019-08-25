package spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.models.Company;
import spring.models.Customer;
import spring.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	
	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("/createCompany")
	public ResponseEntity<Company> createCompany (@RequestBody Company company) throws Exception{
		Company company2 = adminService.createCompany(company);
		ResponseEntity<Company> result = new ResponseEntity<Company>(company2,HttpStatus.OK);
		return result;
	}
	
	@DeleteMapping("/deleteCompany/{id}")
	public void deleteCompany(@PathVariable long id) {
		Company company = null;
		company = adminService.companyById(id);
		if (company!=null) {
			 adminService.deleteCompany(id);
		}
	}
	
	
	@PostMapping("/updateCompany")
	public ResponseEntity<Company> updateCompany(@RequestParam long id, @RequestParam String password, @RequestParam String email) {
		Company company = null;
		company = adminService.companyById(id);
		if (company !=null) {
			adminService.updateCompany(company, password, email);
			ResponseEntity<Company> result = new ResponseEntity<>(company,HttpStatus.OK);
			return result;
		}
		return null;
	}
	
	@GetMapping("/getAllCompanies")
	public ResponseEntity<List<Company>> allCompanies(){
		ResponseEntity<List<Company>> result = new ResponseEntity<List<Company>>(adminService.allCompanies(), HttpStatus.OK);
		return result;
	}
	
	
	@GetMapping("/companyById/{id}")
	public Company companyById(@PathVariable long id) {
		return adminService.companyById(id);
	}
	
	
	@PostMapping("/createCustomer")
	public ResponseEntity<Customer> createCustomer (@RequestBody Customer customer) throws Exception{
		Customer customer2 = adminService.createCustomer(customer);
		ResponseEntity<Customer> result = new ResponseEntity<Customer>(customer2,HttpStatus.OK);
		return result;
	}
	
	@DeleteMapping("/deleteCustomer/{id}")	
	public void deleteCustomer(@PathVariable long id) {
		 adminService.deleteCustomer(id);
	}
	
	@PostMapping("/updateCustomer")
	public ResponseEntity<Customer> updateCustomer(@RequestParam long id, @RequestParam String password){
		Customer customer = null;
		customer = adminService.customerById(id);
		if (customer != null) {
			adminService.updateCustomer(customer, password);
			ResponseEntity<Customer> result = new ResponseEntity<>(customer,HttpStatus.OK);
			return result;	
		}
		return null;
	}
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> findAll(){ 
		ResponseEntity<List<Customer>> result = new ResponseEntity<List<Customer>>(adminService.allCustomers(), HttpStatus.OK);
		return result;
	}

	@GetMapping("/customerById/{id}")
	public Customer customerById(@PathVariable long id) {
		return adminService.customerById(id);
	}
}
