package com.training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.model.Customer;
import com.training.model.InjectionResult;
import com.training.model.Vaccine;

@Repository
public interface InjectionResultRepository extends JpaRepository<InjectionResult, Long>, InjectionResultCustomRepository {

	List<InjectionResult> findAll();

	Optional<InjectionResult> findByInjectionResultId(long id);

	@Query(value = "select ir from InjectionResult ir where ir.customerStr like '%'||:keyword||'%' or ir.vaccineStr like '%'||:keyword||'%'")
	Page<InjectionResult> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

	void deleteByCustomer(Customer customer);

	void deleteByVaccine(Vaccine vaccine);
	
	@Query("SELECT DISTINCT YEAR(i.injectionDate) FROM InjectionResult i ORDER BY YEAR(i.injectionDate)")
	List<Integer> getListYear();

	@Query("SELECT ISNULL(SUM(CASE WHEN MONTH(ci.injectionDate) = 1 THEN ci.numberOfInjection END), 0)"
    		+ " ,ISNULL(SUM(CASE WHEN MONTH(ci.injectionDate) = 2 THEN ci.numberOfInjection END), 0)"
    		+ " ,ISNULL(SUM(CASE WHEN MONTH(ci.injectionDate) = 3 THEN ci.numberOfInjection END), 0)"
    		+ " ,ISNULL(SUM(CASE WHEN MONTH(ci.injectionDate) = 4 THEN ci.numberOfInjection END), 0)"
    		+ " ,ISNULL(SUM(CASE WHEN MONTH(ci.injectionDate) = 5 THEN ci.numberOfInjection END), 0)"
    		+ " ,ISNULL(SUM(CASE WHEN MONTH(ci.injectionDate) = 6 THEN ci.numberOfInjection END), 0)"
    		+ " ,ISNULL(SUM(CASE WHEN MONTH(ci.injectionDate) = 7 THEN ci.numberOfInjection END), 0)"
    		+ " ,ISNULL(SUM(CASE WHEN MONTH(ci.injectionDate) = 8 THEN ci.numberOfInjection END), 0)"
    		+ " ,ISNULL(SUM(CASE WHEN MONTH(ci.injectionDate) = 9 THEN ci.numberOfInjection END), 0)"
    		+ " ,ISNULL(SUM(CASE WHEN MONTH(ci.injectionDate) = 10 THEN ci.numberOfInjection END), 0)"
    		+ " ,ISNULL(SUM(CASE WHEN MONTH(ci.injectionDate) = 11 THEN ci.numberOfInjection END), 0)"
    		+ " ,ISNULL(SUM(CASE WHEN MONTH(ci.injectionDate) = 12 THEN ci.numberOfInjection END), 0)"
    		+ " FROM InjectionResult ci WHERE YEAR(ci.injectionDate) = :year")
    List<Object[]> getlistByYear(@Param("year") int year);

}
