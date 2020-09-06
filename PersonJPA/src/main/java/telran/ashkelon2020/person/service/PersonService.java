package telran.ashkelon2020.person.service;

import telran.ashkelon2020.person.dto.PersonDTO;
import telran.ashkelon2020.person.dto.UppPersonDTO;
import telran.ashkelon2020.person.model.Person;

public interface PersonService {
	
 boolean addPerson(PersonDTO personDTO);
 
 PersonDTO findByID(int id);
 
 PersonDTO updatePerson(int id, UppPersonDTO uppPersonDTO);

 PersonDTO deletePerson(int id);

 Iterable<Person> allPersonByName(String name);

 Iterable<Person> allPersonByAge(int formAge,int toAge);
 
}
