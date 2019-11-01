package spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.models.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

	List<Income> findAllByClientId(long clientId);

}
