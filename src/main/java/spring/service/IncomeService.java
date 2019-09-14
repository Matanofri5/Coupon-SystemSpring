package spring.service;

import java.util.List;

import spring.models.Income;

public interface IncomeService {

	Income storeIncome(Income income);

	List<Income> allIncome();

	List<Income> viewIncomeByCustomer(long customerId) throws Exception;

	List<Income> viewIncomeByCompany(long companyId) throws Exception;


}
