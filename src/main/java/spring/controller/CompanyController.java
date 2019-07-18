package spring.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.models.Company;
import spring.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Resource
	private CompanyService companyService;

	@GetMapping("/all")
	public ResponseEntity<List<Company>> findAll(){ 
		ResponseEntity<List<Company>> result = new ResponseEntity<List<Company>>(companyService.findAll(), HttpStatus.OK);
		return result;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Company> createCompany(@RequestBody Company company){ 
		System.out.println(company + "1");
		Company company2 = companyService.create(company);
		System.out.println(company + "2");

		ResponseEntity<Company> result = new ResponseEntity<Company>(company2, HttpStatus.OK);
		return result;
	}
}
