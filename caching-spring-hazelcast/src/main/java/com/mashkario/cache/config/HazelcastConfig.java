package com.mashkario.cache.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.mashkario.cache.dto.Person;
import com.mashkario.cache.serializer.PersonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

//    @Bean
    HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance(createConfig());
    }

    private Config createConfig() {
        Config config = new Config();
        config.getSerializationConfig().addSerializerConfig(serializerConfig());
        return config;
    }

    private SerializerConfig serializerConfig(){
         return new SerializerConfig()
                .setImplementation(new PersonSerializer())
                .setTypeClass(Person.class);
    }

    @Bean
    HazelcastInstance hazelcastClient() {
        return HazelcastClient.getOrCreateHazelcastClient(clientConfig());
    }

    private ClientConfig clientConfig(){
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("test-cluster");
        clientConfig.setInstanceName("spring-hazelcast-client");
        clientConfig.getSerializationConfig().addSerializerConfig(serializerConfig());
        return clientConfig;

    }

}
