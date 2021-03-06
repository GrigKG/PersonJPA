package telran.ashkelon2020.person.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.ashkelon2020.person.model.Address;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO extends PersonDTO {
	String company;
	Integer salary;
	public EmployeeDTO(Integer id, String name, LocalDate birthDate, Address address, String company, Integer salary) {
		super(id, name, birthDate, address);
		this.company = company;
		this.salary = salary;
	}

	
}
