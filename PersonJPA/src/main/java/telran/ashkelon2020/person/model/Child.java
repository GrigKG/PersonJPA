package telran.ashkelon2020.person.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Child extends Person {
	String kinderGarten;

	public Child(Integer id, String name, LocalDate birthDate, Address address, String kinderGarten) {
		super(id, name, birthDate, address);
		this.kinderGarten = kinderGarten;
	}
}
