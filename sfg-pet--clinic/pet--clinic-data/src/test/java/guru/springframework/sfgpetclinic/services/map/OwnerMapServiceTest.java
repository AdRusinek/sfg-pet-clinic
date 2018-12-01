package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    Long id = 2L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        // mimic dependency injection
        ownerMapService = new OwnerMapService(new PetTypeMapService(),new PetMapService());
        ownerMapService.save(Owner.builder().id(id).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(1,owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(id);

        assertEquals(id,owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;

        Owner owner2 = Owner.builder().id(id).build();

        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(id,savedOwner.getId());

    }

    @Test
    void saveNoId() {

        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(id));
        assertEquals(0,ownerMapService.findAll().size());

    }

    @Test
    void deleteById() {

        ownerMapService.deleteById(id);

        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(lastName);

        assertNotNull(owner);

        assertEquals(id,owner.getId());

    }
}