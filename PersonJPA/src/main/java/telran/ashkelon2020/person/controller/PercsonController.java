package telran.ashkelon2020.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2020.person.dto.CityPopulationDTO;
import telran.ashkelon2020.person.dto.PersonDTO;
import telran.ashkelon2020.person.dto.UppPersonDTO;
import telran.ashkelon2020.person.model.Person;
import telran.ashkelon2020.person.service.PersonService;

@RestController
@RequestMapping("/person")
public class PercsonController implements PersonService {
	@Autowired
	PersonService personService;

	@Override
	@PostMapping
	public boolean addPerson(@RequestBody PersonDTO personDTO) {
		return personService.addPerson(personDTO);
	}

	@Override
	@GetMapping("/{id}")
	public PersonDTO findByID(@PathVariable int id) {
		return personService.findByID(id);
	}

	@Override
	@PutMapping("/{id}")
	public PersonDTO updatePerson(@PathVariable int id,@RequestBody UppPersonDTO uppPersonDTO) {
		return personService.updatePerson(id, uppPersonDTO);
	}

	@Override
	@DeleteMapping("/{id}")
	public PersonDTO deletePerson(@PathVariable int id) {
		return personService.deletePerson(id);
	}

	@Override
	@GetMapping("/allPerson/{name}")
	public Iterable<Person> allPersonByName(@PathVariable String name) {
		return personService.allPersonByName(name);
	}

	@Override
	@GetMapping("/agePeriod/{formAge}/{toAge}")
	public Iterable<Person> allPersonByAge(@PathVariable int formAge,@PathVariable int toAge) {
		return personService.allPersonByAge(formAge, toAge);
	}

	@Override
	@GetMapping("/city/{city}")
	public Iterable<PersonDTO> findByCity(@PathVariable String city) {
		return personService.findByCity(city);
	}

	@Override
	@GetMapping("/population/city")
	public Iterable<CityPopulationDTO> getCityPopulation() {
		return personService.getCityPopulation();
	}

	@Override
	@GetMapping("/salary/{min}/{max}")
	public Iterable<PersonDTO> findEmployeeBySalary(int min, int max) {
		return personService.findEmployeeBySalary(min, max);
	}

	@Override
	@GetMapping("/allChildren")
	public Iterable<PersonDTO> getChildren() {
		return personService.getChildren();
	}
	
	
}
