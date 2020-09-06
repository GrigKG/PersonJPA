package telran.ashkelon2020.person.dao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.ashkelon2020.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	Iterable<Person> findAllByName(String name);
	Iterable<Person> findByBirthDateBetween(LocalDate from, LocalDate to);
}