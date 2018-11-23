package guru.springframework.sfgpetclinic.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

//this established this is a base class for JPA ( we are saying we are gonna inherit from this class or
// other classes are going to be inheriting it I don't need this specific class mapped to the DB
// this won't be created in the DB
// all classes in package model inherit from it
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // NR 3
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    // DB gonna to provide for us the ID value
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
