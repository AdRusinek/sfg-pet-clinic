package guru.springframework.sfgpetclinic.model;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

//this established this is a base class for JPA ( we are saying we are gonna inherit from this class or
// other classes are going to be inheriting it I don't need this specific class mapped to the DB
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    // DB gonna to provide for us the ID value
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
