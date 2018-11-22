package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

// those generic types because we are gonna use methods inside CR class so we have to make sure that they
// will return right thing
public interface OwnerRepository extends CrudRepository<Owner,Long> {

}
