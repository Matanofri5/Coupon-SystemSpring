package spring.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
