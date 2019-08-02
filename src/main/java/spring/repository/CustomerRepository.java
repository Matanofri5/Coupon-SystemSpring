package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
