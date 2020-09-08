package telran.ashkelon2020.person.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.ashkelon2020.person.dao.PersonRepository;
import telran.ashkelon2020.person.dto.CityPopulationDTO;
import telran.ashkelon2020.person.dto.PersonDTO;
import telran.ashkelon2020.person.dto.UppPersonDTO;
import telran.ashkelon2020.person.dto.exception.ForbiddenException;
import telran.ashkelon2020.person.dto.exception.PersonNotFoundException;
import telran.ashkelon2020.person.model.Child;
import telran.ashkelon2020.person.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	ModelMapper modelMapper;

	private String PATH_MODEL = "telran.ashkelon2020.person.model.";
	private String PATH_DTO = "telran.ashkelon2020.person.dto.";


	private Person getPersonByDTO(PersonDTO personDTO) {
		String className = personDTO.getClass().getSimpleName().replaceAll("DTO", "");
		Person person = null;
		try {
			person = (Person) modelMapper.map(personDTO, Class.forName(PATH_MODEL + className));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return person;
	}
	
	private PersonDTO getDTOByPerson(Person person) {
		String className = person.getClass().getSimpleName();
		PersonDTO personDTO = null;
		try {
			personDTO = (PersonDTO) modelMapper.map(person, Class.forName(PATH_DTO + className+"DTO"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return personDTO;
	}
	
	@Override
	@Transactional
	public boolean addPerson(PersonDTO personDTO) {
		if(personDTO.getName()==null||personDTO.getBirthDate()==null) return false;
		if(personRepository.existsById(personDTO.getId())) return false;
		personRepository.save(getPersonByDTO(personDTO));
		return true;
	}

	@Override
	public PersonDTO findByID(int id) {
		Person person =personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		return getDTOByPerson(person);
	}

	@Override
	@Transactional
	public PersonDTO updatePerson(int id, UppPersonDTO uppPersonDTO) {
		Person person =personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		String name = uppPersonDTO.getName();
		if(name == null||name == person.getName()) return null;
		person.setName(uppPersonDTO.getName());
		personRepository.save(person);
		return getDTOByPerson(person);
	}

	@Override
	public PersonDTO deletePerson(int id) {
		Person person =personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		personRepository.deleteById(id);
		return getDTOByPerson(person);
	}

	@Override
	@Transactional(readOnly = true)
	public List <Person> allPersonByName(String name) {
		return personRepository.findAllByName(name)
		.collect(Collectors.toList());

	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Person> allPersonByAge(int formAge, int toAge) {
		if(formAge > toAge||formAge<0||toAge<0) throw new ForbiddenException();
		LocalDate from = LocalDate.now().minusYears(toAge);
		LocalDate to = LocalDate.now().minusYears(formAge);
		System.out.println("from" + from + "to" + to);
		return   personRepository.findByBirthDateBetween(from, to)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDTO> findByCity(String city) {
		return personRepository.findByAddressCity(city)
				.map(p->modelMapper.map(p, PersonDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<CityPopulationDTO> getCityPopulation() {
		return personRepository.getCityPopulation();
	}

	@Override
	public Iterable<PersonDTO> findEmployeeBySalary(int min, int max) {
		return personRepository.findBySalaryBetween(min, max)
				.map(p->modelMapper.map(p, PersonDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDTO> getChildren() {
		//return personRepository.findChildrenBy().map(p->modelMapper.map(p, PersonDTO.class)).collect(Collectors.toList());
		
	    Example<Child> example = Example.of(new Child());
		return personRepository.findAll(example).stream()
				.map(p->modelMapper.map(p, PersonDTO.class))
				.collect(Collectors.toList());
	}

	
}
