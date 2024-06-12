package com.mashkario.cache.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.mashkario.cache.dto.Person;
import com.mashkario.cache.service.HazelcastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collection;

@ExtendWith(SpringExtension.class)
class PersonControllerTest {

    @Mock
    private HazelcastService hazelcastService;

    @InjectMocks
    private PersonController personController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    void getAllPersons_success() throws Exception {
        Collection<Person> persons = Arrays.asList(new Person("John", "Doe"), new Person("Jane", "Smith"));
        when(hazelcastService.getAll()).thenReturn(persons);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/persons")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals("[{\"name\":\"John\",\"surname\":\"Doe\"},{\"name\":\"Jane\",\"surname\":\"Smith\"}]", result.getResponse().getContentAsString());
    }

    @Test
    void getPersonById_notFound() throws Exception {
        when(hazelcastService.get("1")).thenReturn(null);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/persons/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    void addPerson_success() throws Exception {
        Person person = new Person("John", "Doe");
        when(hazelcastService.put("1", person)).thenReturn(person);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/api/persons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John\",\"surname\":\"Doe\"}");

        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals("{\"name\":\"John\",\"surname\":\"Doe\"}", result.getResponse().getContentAsString());
    }
}
