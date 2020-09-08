package telran.ashkelon2020.person.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.ashkelon2020.person.model.Address;

@NoArgsConstructor
@Getter
@Setter
public class ChildDTO extends PersonDTO {
	String kinderGarten;

	public ChildDTO(Integer id, String name, LocalDate birthDate, Address address, String kinderGarten) {
		super(id, name, birthDate, address);
		this.kinderGarten = kinderGarten;
	}
}
