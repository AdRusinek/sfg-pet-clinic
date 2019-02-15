package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

    // Whole desire of working with builder pattern is that it is very convenience while creating a objects
    @Builder // in case of working builder with inheritance we have to be careful NR 1 (look in person)
    public Owner(Long id, String firstName, String lastName,String address, String city,
                 String telephone, Set<Pet> pets) {
      super(id,firstName,lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;

        if(pets != null) {
            this.pets = pets;
        }
    }

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    // If i delete owner, then that is going to cascade down
    // if i have pets and i delete the owner of the pet, the pets will also get deleted
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner" )
    private Set<Pet> pets = new HashSet<>();

    public Pet getPet(String name) {
        return getPet(name,false);
    }

    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Pet pet : pets) {
            if (!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }
}
