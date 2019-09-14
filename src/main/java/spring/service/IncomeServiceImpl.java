package spring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.models.Income;
import spring.repository.IncomeRepository;

@Service
public class IncomeServiceImpl implements IncomeService{

	@Autowired
	private IncomeRepository incomeRepository;
	
	@Override
	public Income storeIncome (Income income) {
		incomeRepository.save(income);
		return income;
	}
	
	@Override
	public List<Income> allIncome(){
		return incomeRepository.findAll();
	}
	

	@Override
	public List<Income> viewIncomeByCustomer(long customerId) throws Exception{
		try {
			List<Income> allIncomesByCustomer = incomeRepository.findAllByClientId(customerId);
			return allIncomesByCustomer;
		} catch (Exception e) {
			throw new Exception("Fialed to Get all incomes by customer " + customerId);
		}
	}
	
	@Override
	public List<Income> viewIncomeByCompany(long companyId) throws Exception{
		try {
			List<Income> allIncomesByCompany = incomeRepository.findAllByClientId(companyId);
			return allIncomesByCompany;
		} catch (Exception e) {
			throw new Exception("Fialed to Get all incomes by company " + companyId);
		}
	}
	
	
}
