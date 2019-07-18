package spring.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import spring.models.Company;
import spring.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Resource
	private CompanyRepository companyRepository;
	
	/* (non-Javadoc)
	 * @see spring.service.CompanyService#findAll()
	 */
	@Override
	public List<Company> findAll(){
		return companyRepository.findAll();
	}

	@Override
	public Company create(Company company) {
		companyRepository.save(company);
		return company;
	}
}
