package com.mashkario.cache.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.memory.MemorySize;
import com.hazelcast.memory.MemoryUnit;
import com.mashkario.cache.dto.Person;
import com.mashkario.cache.serializer.PersonSerializer;
import com.mashkario.cache.service.HazelcastService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance(createConfig());
    }

    private Config createConfig() {
        Config config = new Config();
        config.addMapConfig(mapConfig());
        // native is an enterprise feature
//        config.setNativeMemoryConfig(nativeMemoryConfig());
        config.getSerializationConfig().addSerializerConfig(serializerConfig());
        return config;
    }

//    private NativeMemoryConfig nativeMemoryConfig() {
//        NativeMemoryConfig nativeMemoryConfig = new NativeMemoryConfig();
//        nativeMemoryConfig.setEnabled(true);
//        nativeMemoryConfig.setAllocatorType(NativeMemoryConfig.MemoryAllocatorType.POOLED);
//        nativeMemoryConfig.setSize(MemorySize.parse("4", MemoryUnit.GIGABYTES));
//        return nativeMemoryConfig;
//    }

    private MapConfig mapConfig() {
        MapConfig mapConfig = new MapConfig(HazelcastService.MAP_NAME);
//        mapConfig.setInMemoryFormat(InMemoryFormat.NATIVE);

        return mapConfig;
    }

    private SerializerConfig serializerConfig() {
        return new SerializerConfig()
                .setImplementation(new PersonSerializer())
                .setTypeClass(Person.class);
    }

//    @Bean
    HazelcastInstance hazelcastClient() {
        return HazelcastClient.getOrCreateHazelcastClient(clientConfig());
    }

    private ClientConfig clientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("test-cluster");
        clientConfig.setInstanceName("spring-hazelcast-client");
        clientConfig.getSerializationConfig().addSerializerConfig(serializerConfig());
        return clientConfig;

    }

}
