package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company findByCompanyName(String companyName);

	Company findCompanyByCompanyNameAndPassword(String companyName, String password);

}
