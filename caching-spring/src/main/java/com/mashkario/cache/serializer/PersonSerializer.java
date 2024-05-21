package com.mashkario.cache.serializer;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import com.mashkario.cache.dto.Person;

import java.io.IOException;

public class PersonSerializer implements StreamSerializer<Person> {

    @Override
    public void write(ObjectDataOutput out, Person person) throws IOException {
        out.writeString(person.name());
        out.writeString(person.surname());
    }

    @Override
    public Person read(ObjectDataInput in) throws IOException {
        return new Person(in.readString(), in.readString());
    }

    @Override
    public int getTypeId() {
        return 1;
    }
}
