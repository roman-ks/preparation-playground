package com.mashkario.cache.rest;

import com.mashkario.cache.dto.Person;
import com.mashkario.cache.service.HazelcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final HazelcastService hazelcastService;

//ollama run codellama:7b "write unit test with junit5 and spring test artifacts for class $(cat caching-spring/src/main/java/com/mashkario/cache/rest/PersonController.java)"

    @Autowired
    public PersonController(HazelcastService hazelcastService) {
        this.hazelcastService = hazelcastService;
    }

    // GET method to retrieve all persons
    @GetMapping
    public Collection<Person> getAllPersons() {
        return hazelcastService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable String id) {
        Person person = hazelcastService.get(id);
        if (person!=null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST method to add a new person
    @PutMapping("/{id}")
    public Person addPerson(@PathVariable String id, @RequestBody Person person) {
        return hazelcastService.put(id, person);
    }

    // POST method to update an existing person. return response entity
    @PostMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable String id, @RequestBody Person person) {
        Person updatedPerson = hazelcastService.put(id, person);
        if (updatedPerson!=null) {
            return ResponseEntity.ok(updatedPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
