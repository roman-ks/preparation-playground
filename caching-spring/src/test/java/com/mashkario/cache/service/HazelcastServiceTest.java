//package com.mashkario.cache.service;
//
//import com.hazelcast.map.IMap;
//import com.mashkario.cache.dto.Person;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.cache.CacheManager;
//import com.hazelcast.core.HazelcastInstance;
//
//import java.util.Arrays;
//import java.util.Collection;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//public class HazelcastServiceTest {
//
//    @Autowired
//    private HazelcastService hazelcastService;
//
//    @MockBean
//    private CacheManager cacheManager;
//
//    @MockBean
//    private HazelcastInstance hazelcastInstance;
//
//    @Test
//    public void put_validIdAndPerson_returnsPerson() {
//        String id = "1234";
//        Person person = new Person("John", "Doe");
//        IMap<String, Person> map = hazelcastInstance.getMap(HazelcastService.MAP_NAME);
//        when(map.put(id, person)).thenReturn(person);
//
//        Person result = hazelcastService.put(id, person);
//
//        assertEquals(person, result);
//    }
//
//    @Test
//    public void get_validId_returnsPerson() {
//        String id = "1234";
//        Person person = new Person("John", "Doe");
//        IMap<String, Person> map = hazelcastInstance.getMap(HazelcastService.MAP_NAME);
//        when(hazelcastInstance.getMap(HazelcastService.MAP_NAME)).thenReturn(map);
//        when(map.get(id)).thenReturn(person);
//
//        Person result = hazelcastService.get(id);
//
//        assertEquals(person, result);
//    }
//
//    @Test
//    public void getAll_validId_returnsPersons() {
//        IMap<String, Person> map = hazelcastInstance.getMap(HazelcastService.MAP_NAME);
//        when(hazelcastInstance.getMap(HazelcastService.MAP_NAME)).thenReturn(map);
//        Collection<Person> persons = Arrays.asList(new Person("John", "Doe"), new Person("Jane", "Smith"));
//        when(map.values()).thenReturn(persons);
//
//        Collection<Person> result = hazelcastService.getAll();
//
//        assertEquals(persons, result);
//    }
//}
