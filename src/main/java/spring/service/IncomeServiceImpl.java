package spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import spring.models.Income;
import spring.repository.IncomeRepository;

@Service
public class IncomeServiceImpl implements IncomeService{
	@Resource
	private IncomeRepository incomeRepository;
	
	@Override
	public Income stroeIncome (Income income) {
		incomeRepository.save(income);
		return income;
	}

	
}
