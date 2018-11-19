package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Speciality;

// Just a note here (this extends CrudService so it give us standard crud operations) like with all our Services
public interface SpecialtiesService extends CrudService<Speciality, Long> {
}
