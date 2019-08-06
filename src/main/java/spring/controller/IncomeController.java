package spring.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.models.Coupon;
import spring.models.Income;
import spring.service.IncomeService;

@RestController
@RequestMapping("/Inome")
public class IncomeController {

	@Resource
	public IncomeService incomeService;
	
	@PostMapping("/createIncome")
	public ResponseEntity<Income> storeIncome(@RequestBody Income income){
		Income income2 = incomeService.stroeIncome(income);
		ResponseEntity<Income> result = new ResponseEntity<Income>(income2, HttpStatus.OK);
		return result;
	}
	
}
