package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

/**
 *
 * @param <T> extend BaseEntity because we need to have access to the getID() method from that Class
 *           and this way we also ensure that every Object will have this method
 * @param <ID> it extends Long because we need it to implement autoGen ID
 */
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long>  {

    /**
     * comment test
     * Long because it will allow us to use nextID() method.
     */
    protected Map<Long,T> map = new HashMap<>();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id)   {
        return map.get(id);
    }

    T save(T object) {
        if(object !=  null) {
            if(object.getId() == null) {
                object.setId(getNextID());
            }
            map.put(object.getId(),object);
        } else {
            throw new RuntimeException("Object cannot be null");
        }
        return object;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    // Our entities will have to implement proper equals method
    void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    /**
     *  If the ID has never been initialized before it will throw the exception
     *  and to handle it catch block will implement this ID as 1Long
     *  Collections.max(map.keySet()) + 1 -> it is checking the size of list and generate
     *  next value if it is not null
     * @return next ID value
     */
    private Long getNextID() {

        Long nextID = null;

        try {
            nextID = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException ex) {
            nextID = 1L;
        }


        return nextID;
    }
}
