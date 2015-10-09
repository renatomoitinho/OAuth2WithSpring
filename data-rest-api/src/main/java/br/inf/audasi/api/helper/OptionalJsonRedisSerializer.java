package br.inf.audasi.api.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class OptionalJsonRedisSerializer implements RedisSerializer<Optional<?>> {

    private ObjectMapper objectMapper;

    public OptionalJsonRedisSerializer() {
        objectMapper = new ObjectMapper()
                .registerModule(new JSR310Module())
                .registerModule(new Jdk8Module());
    }

    @Override
    public byte[] serialize(Optional<?> optional) throws SerializationException {
        try {
            return objectMapper.writeValueAsBytes(optional.get());
        } catch (IOException e) {
            throw new SerializationException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<?> deserialize(byte[] bytes) throws SerializationException {

        if(Objects.isNull(bytes)){
            return null;
        }
        try {
             return Optional.of(objectMapper.readValue(bytes, 0, bytes.length, Object.class));
        } catch (Exception e) {
            throw new SerializationException(e.getMessage(), e);
        }
    }

}