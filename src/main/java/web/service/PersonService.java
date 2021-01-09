package web.service;

import web.model.Person;
import web.model.Role;

import java.util.List;

public interface PersonService {

    List<Person> index();
    Person show(Long  id);
    boolean save(Person person, Role role);
    void update(Long  id, Person updatedPerson);
    void delete(Long  id);
    Person findByName(String username);
}