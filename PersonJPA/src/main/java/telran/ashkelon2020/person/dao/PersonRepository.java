package telran.ashkelon2020.person.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.ashkelon2020.person.dto.CityPopulationDTO;
import telran.ashkelon2020.person.model.Child;
import telran.ashkelon2020.person.model.Employee;
import telran.ashkelon2020.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	// @Query(value = "select * from persons where name=?1", nativeQuery=true)
	//@Query("select p from Person p where p.name=?1")
	Stream<Person> findAllByName(String name);

	Stream<Person> findByBirthDateBetween(LocalDate from, LocalDate to);

	//@Query("select p from Person p where p.address.city=?1")
	Stream<Person> findByAddressCity(String city);

	@Query("select new telran.ashkelon2020.person.dto.CityPopulationDTO(p.address.city, count(p)) from Person p group by p.address.city order by count(p) desc")
	List<CityPopulationDTO> getCityPopulation();

	@Query("select e from Employee e where e.salary between ?1 and ?2")
	Stream<Employee> findBySalaryBetween(int min, int max);

	Stream<Child> findChildrenBy();}
