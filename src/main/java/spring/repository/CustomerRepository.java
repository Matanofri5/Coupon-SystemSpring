package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
