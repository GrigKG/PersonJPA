package telran.ashkelon2020.person.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.ashkelon2020.person.dao.PersonRepository;
import telran.ashkelon2020.person.dto.PersonDTO;
import telran.ashkelon2020.person.dto.UppPersonDTO;
import telran.ashkelon2020.person.dto.exception.ForbiddenException;
import telran.ashkelon2020.person.dto.exception.PersonNotFoundException;
import telran.ashkelon2020.person.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public boolean addPerson(PersonDTO personDTO) {
		if(personDTO.getName()==null||personDTO.getBirthDate()==null) return false;
		if(personRepository.existsById(personDTO.getId())) return false;
		Person person = modelMapper.map(personDTO, Person.class);
		personRepository.save(person);
		return true;
	}

	@Override
	public PersonDTO findByID(int id) {
		Person person =personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		return modelMapper.map(person, PersonDTO.class);
	}

	@Override
	public PersonDTO updatePerson(int id, UppPersonDTO uppPersonDTO) {
		Person person =personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		String name = uppPersonDTO.getName();
		if(name == null||name == person.getName()) return null;
		person.setName(uppPersonDTO.getName());
		personRepository.save(person);
		return modelMapper.map(person, PersonDTO.class);
	}

	@Override
	public PersonDTO deletePerson(int id) {
		Person person =personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		personRepository.deleteById(id);
		return modelMapper.map(person, PersonDTO.class);
	}

	@Override
	public Iterable <Person> allPersonByName(String name) {
		return personRepository.findAllByName(name);
	}

	@Override
	public Iterable<Person> allPersonByAge(int formAge, int toAge) {
		if(formAge > toAge||formAge<0||toAge<0) throw new ForbiddenException();
		LocalDate from = LocalDate.now().minusYears(toAge);
		LocalDate to = LocalDate.now().minusYears(formAge);
		System.out.println("from" + from + "to" + to);
		return  personRepository.findByBirthDateBetween(from, to);
	}

}
