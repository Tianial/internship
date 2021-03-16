package com.parcaune.demo.dao;

import com.parcaune.demo.model.Person;
import org.graalvm.compiler.nodes.java.ArrayLengthNode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakePersonDataAcessService implements PersonDao {
    private static List<Person> DB =new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }
}
