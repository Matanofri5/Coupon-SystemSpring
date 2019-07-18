package spring.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import spring.models.Customer;
import spring.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Resource
	private CustomerRepository customerRepository;
	
	/* (non-Javadoc)
	 * @see spring.service.CustomerService#findAll()
	 */
	@Override
	public List<Customer> findAll(){
		return customerRepository.findAll();
	}
}