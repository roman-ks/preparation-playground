package com.mashkario.cache.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.mashkario.cache.dto.Person;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class HazelcastService {

    private static final String MAP_NAME = "People";

    private final HazelcastInstance hazelcastInstance;

    public HazelcastService(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public Person put(String id, Person person) {
        IMap<String, Person> map = hazelcastInstance.getMap(MAP_NAME);
        return map.putIfAbsent(id, person);
    }

    public Person get(String id) {
        IMap<String, Person> map = hazelcastInstance.getMap(MAP_NAME);
        return map.get(id);
    }

    public Collection<Person> getAll() {
        return hazelcastInstance.<String, Person>getMap(MAP_NAME).values();
    }

}
