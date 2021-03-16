package com.parcaune.demo.dao;

import com.parcaune.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository("fakeDao")
public class FakePersonDataAcessService implements PersonDao {
    private static List<Person> DB =new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        Person personToInsert = new Person(id, person.getName());
        DB.add(personToInsert);
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }
}
