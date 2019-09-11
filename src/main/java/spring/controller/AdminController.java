package spring.controller;

import java.util.List;
import java.util.Map;

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

import spring.Session;
import spring.models.Company;
import spring.models.Customer;
import spring.models.Income;
import spring.service.AdminService;
import spring.service.AdminServiceImpl;
import spring.service.IncomeService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private IncomeService incomeService;

	@Autowired
	private Map<String, Session> tokens;

	private Session exists(String token) {
		System.out.println("1 - " + token);
		System.out.println("2 - " + LoginController.tokens);
//		return tokens.get(token);
		return LoginController.tokens.get(token);
	}

	@PostMapping("/createCompany/{token}")
	public ResponseEntity<String> createCompany(@RequestBody Company company, @PathVariable String token)throws Exception {
		Session session = exists(token);
		System.out.println("my session = "+ session);
		if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				((AdminServiceImpl) session.getFacade()).createCompany(company);
				return new ResponseEntity<>("company created", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage() + e.getStackTrace(), HttpStatus.UNAUTHORIZED);
			}
		}
		return null;
	}

	@DeleteMapping("/deleteCompany/{id}")
	public void deleteCompany(@PathVariable long id) {
		Company company = null;
		company = adminService.companyById(id);
		if (company != null) {
			adminService.deleteCompany(id);
		}
	}

	@PostMapping("/updateCompany")
	public ResponseEntity<Company> updateCompany(@RequestParam long id, @RequestParam String password,
			@RequestParam String email) {
		Company company = null;
		company = adminService.companyById(id);
		if (company != null) {
			adminService.updateCompany(company, password, email);
			ResponseEntity<Company> result = new ResponseEntity<>(company, HttpStatus.OK);
			return result;
		}
		return null;
	}

	@GetMapping("/getAllCompanies")
	public ResponseEntity<List<Company>> allCompanies() {
		ResponseEntity<List<Company>> result = new ResponseEntity<List<Company>>(adminService.allCompanies(),
				HttpStatus.OK);
		return result;
	}

	@GetMapping("/companyById/{id}")
	public Company companyById(@PathVariable long id) {
		return adminService.companyById(id);
	}

	@PostMapping("/createCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws Exception {
		Customer customer2 = adminService.createCustomer(customer);
		ResponseEntity<Customer> result = new ResponseEntity<Customer>(customer2, HttpStatus.OK);
		return result;
	}

	@DeleteMapping("/deleteCustomer/{id}")
	public void deleteCustomer(@PathVariable long id) {
		adminService.deleteCustomer(id);
	}

	@PostMapping("/updateCustomer")
	public ResponseEntity<Customer> updateCustomer(@RequestParam long id, @RequestParam String password) {
		Customer customer = null;
		customer = adminService.customerById(id);
		if (customer != null) {
			adminService.updateCustomer(customer, password);
			ResponseEntity<Customer> result = new ResponseEntity<>(customer, HttpStatus.OK);
			return result;
		}
		return null;
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> findAll() {
		ResponseEntity<List<Customer>> result = new ResponseEntity<List<Customer>>(adminService.allCustomers(),
				HttpStatus.OK);
		return result;
	}

	@GetMapping("/customerById/{id}")
	public Customer customerById(@PathVariable long id) {
		return adminService.customerById(id);
	}
	
	@GetMapping("/allIncome")
	public ResponseEntity<List<Income>> allIncome(){
		ResponseEntity<List<Income>> allIncome = new ResponseEntity<List<Income>>(incomeService.allIncome(), HttpStatus.OK);
		return allIncome;
	}
}
