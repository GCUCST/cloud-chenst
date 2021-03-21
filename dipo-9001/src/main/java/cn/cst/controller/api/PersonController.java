package cn.cst.controller.api;

import cn.cst.dao.PersonRepository;
import cn.cst.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@RestController
@RequestMapping(value = "person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping(path = "addPerson")
    public void addPerson(Person person) {
        personRepository.save(person);
    }

    @DeleteMapping(path = "deletePerson")
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

//    @GET
//    @Produces(TYPE_JSON)
//    @Path("getPerson")
    @GetMapping("getPersonByName/{name}")
    public Person getPerson(@PathVariable("name") String name) {
        return personRepository.findByName(name);
    }
}
