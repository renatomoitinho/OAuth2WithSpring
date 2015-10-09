package br.inf.audasi.api.helper;


import br.inf.audasi.domain.entity.auth.Role;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Optional;

public class Mains {

    static class OptionalSerialize extends JsonSerializer<Optional<?>> {

        @Override
        public void serialize(Optional<?> optional, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
           // System.out.println( ((ParameterizedType) optional.getClass().getGenericSuperclass()).getActualTypeArguments() );

            try {
                System.out.println(optional.getClass().getDeclaredField("value").getType());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

//            System.out.println(  ((Class) ((ParameterizedType) optional.getClass()
//                    .getGenericSuperclass()).getActualTypeArguments()[0]) );

        }
    }

    public static void main(String[] args) throws IOException {


        OptionalJsonRedisSerializer jsonRedisSerializer = new OptionalJsonRedisSerializer();

        Optional<Role> user = Optional.of(new Role(){{setName("admin");}});


        byte[] bytes = jsonRedisSerializer.serialize(user);


        Optional<Role> users = (Optional<Role>) jsonRedisSerializer.deserialize(bytes);



        System.out.println( users);
    }


}
