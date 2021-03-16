package com.parcaune.demo.api;

import com.parcaune.demo.model.Person;
import com.parcaune.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/person")   // transforms the request
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping // create data
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping  // take data
    public List<Person> getAllPeople(){
        List<Person> allPeople = personService.getAllPeople();
        return allPeople;
    }
}
