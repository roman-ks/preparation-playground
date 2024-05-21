package com.mashkario.cache.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.mashkario.cache.dto.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class HazelcastService {

    private static final Logger logger = LoggerFactory.getLogger(HazelcastService.class);

    public static final String MAP_NAME = "People";

    private final HazelcastInstance hazelcastInstance;

    public HazelcastService(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @CacheEvict(value = "personsCache", key = "#id")
    public Person put(String id, Person person) {
        IMap<String, Person> map = hazelcastInstance.getMap(MAP_NAME);
        return map.put(id, person);
    }

//    @Cacheable(value = "personsCache", key="#id")
    public Person get(String id) {
        logger.info("Getting person {}", id);
        IMap<String, Person> map = hazelcastInstance.getMap(MAP_NAME);
        return map.get(id);
    }

    public Collection<Person> getAll() {
        return hazelcastInstance.<String, Person>getMap(MAP_NAME).values();
    }

}
