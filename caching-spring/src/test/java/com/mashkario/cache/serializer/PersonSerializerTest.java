package com.mashkario.cache.serializer;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.mashkario.cache.dto.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class PersonSerializerTest {

    @Mock
    private ObjectDataInput input;

    @Mock
    private ObjectDataOutput output;

    @InjectMocks
    private PersonSerializer serializer = new PersonSerializer();

    @Test
    public void testWrite() throws IOException {
        String name = "John";
        String surname = "Doe";
        Person person = new Person(name, surname);

        serializer.write(output, person);

        Mockito.verify(output).writeString(name);
        Mockito.verify(output).writeString(surname);
    }

    @Test
    public void testRead() throws IOException {
        String name = "John";
        String surname = "Doe";

        Mockito.when(input.readString()).thenReturn(name, surname);

        Person person = serializer.read(input);

        Assertions.assertEquals(person.name(), name);
        Assertions.assertEquals(person.surname(), surname);
    }
}
