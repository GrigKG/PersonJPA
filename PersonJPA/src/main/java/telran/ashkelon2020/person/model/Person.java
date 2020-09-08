package telran.ashkelon2020.person.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Builder
@Entity
@Table(name="persons")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {
@Id
Integer id;
String name;
LocalDate birthDate;
//@Embedded
Address address;
}
