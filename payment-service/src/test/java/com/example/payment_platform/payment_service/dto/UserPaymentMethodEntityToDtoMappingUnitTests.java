package com.example.payment_platform.payment_service.dto;

import com.example.payment_platform.common.model.PaymentType;
import com.example.payment_platform.common.model.Provider;
import com.example.payment_platform.common.model.User;
import com.example.payment_platform.common.model.UserPaymentMethod;
import com.example.payment_platform.payment_service.configuration.ModelMapperConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@Import(ModelMapperConfig.class) // Only import the configuration needed
public class UserPaymentMethodEntityToDtoMappingUnitTests {

    @Autowired
    ModelMapper modelMapper;

    @Test
    void testUserPaymentMethodEntityToUserPaymentMethodDtoMapping() {
        OffsetDateTime createdAtUser = OffsetDateTime.of(2024,8,30, 6,5,4, 0, ZoneOffset.UTC);
        OffsetDateTime updatedAtUser = OffsetDateTime.of(2024,8,30, 6,5,4, 0, ZoneOffset.UTC);

        User user = new User(UUID.fromString("ec2b91f7-8a4c-4732-9207-0f47647eee36"),
                "user1",
                "user1@gmail.com",
                "password_hash_1",
                "John Doe",
                "033944939",
                createdAtUser,
                updatedAtUser);

        OffsetDateTime createdAtPaymentMethod = OffsetDateTime.of(2024,8,30, 7,30,0, 0, ZoneOffset.UTC);
        OffsetDateTime updatedAtPaymentMethod = OffsetDateTime.of(2024,8,30, 7,30,0, 0, ZoneOffset.UTC);
        UserPaymentMethod userPaymentMethod = new UserPaymentMethod(
                UUID.fromString("b4e5c0ab-cf5b-4a23-9007-8d39f17c3434"),
                user,
                PaymentType.BANK_ACCOUNT,
                Provider.AMERICAN_EXPRESS,
                "44938302",
                null,
                "Billing Adress 101223",
                false,
                createdAtPaymentMethod,
                updatedAtPaymentMethod);

        UserPaymentMethodDto expectedPaymentMethod = new UserPaymentMethodDto(
                UUID.fromString("b4e5c0ab-cf5b-4a23-9007-8d39f17c3434"),
                PaymentType.BANK_ACCOUNT,
                Provider.AMERICAN_EXPRESS,
                "****8302",
                null,
                false);

        UserPaymentMethodDto resultUserPaymentMethodDto = modelMapper.map(userPaymentMethod, UserPaymentMethodDto.class);
        assertThat(resultUserPaymentMethodDto, is(equalTo(expectedPaymentMethod)));
    }

}
