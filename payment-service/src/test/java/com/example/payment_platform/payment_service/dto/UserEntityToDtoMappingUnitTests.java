package com.example.payment_platform.payment_service.dto;


import com.example.payment_platform.common.model.User;
import com.example.payment_platform.payment_service.configuration.ModelMapperConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@Import(ModelMapperConfig.class) // Only import the configuration needed
public class UserEntityToDtoMappingUnitTests {

    @Autowired
    ModelMapper modelMapper;

    @Test
    void testUserEntityToUserDtoMapping() {
        OffsetDateTime createdAt = OffsetDateTime.of(2024,8,30, 6,5,4, 0, ZoneOffset.UTC);
        OffsetDateTime updatedAt = OffsetDateTime.of(2024,8,30, 6,5,4, 0, ZoneOffset.UTC);

        User user = new User(UUID.fromString("ec2b91f7-8a4c-4732-9207-0f47647eee36"),
                "user1",
                "user1@gmail.com",
                "password_hash_1",
                "John Doe",
                "033944939",
                createdAt,
                updatedAt);

        UserDto expectedUser = new UserDto(UUID.fromString("ec2b91f7-8a4c-4732-9207-0f47647eee36"),
                "user1",
                "user1@gmail.com",
                "John Doe",
                "033944939",
                createdAt,
                updatedAt);

        UserDto resultUserDto = modelMapper.map(user, UserDto.class);
        assertThat(resultUserDto, is(equalTo(expectedUser)));
    }
}
