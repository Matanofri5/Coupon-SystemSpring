package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
