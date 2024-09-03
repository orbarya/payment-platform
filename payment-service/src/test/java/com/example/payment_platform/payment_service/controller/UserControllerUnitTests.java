package com.example.payment_platform.payment_service.controller;

import com.example.payment_platform.payment_service.configuration.ModelMapperConfig;
import com.example.payment_platform.payment_service.dto.UserDto;
import com.example.payment_platform.payment_service.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(OrderAnnotation.class)
@WebMvcTest(
        controllers = UserController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {EntityManagerFactory.class})
)
@Import(ModelMapperConfig.class)
public class UserControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDto user;

    @BeforeEach
    public void setup() {
        user = new UserDto(
                UUID.fromString("74a59dbd-8d78-413c-bd1d-66894ebf5f82"),
                "test_user_1",
                "test@email.com",
                "John Doe",
                "049983384",
                OffsetDateTime.of(2024, 8, 30, 0, 0, 0, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2024, 8, 30, 0, 0, 0, 0, ZoneOffset.UTC)
        );
    }

    @Test
    @Order(1)
    public void testGetUserByIdEndpoint() throws Exception {
        // Mocking the service layer response
        given(userService.get(any())).willReturn(user);

        // Performing the GET request
        ResultActions response = mockMvc.perform(get("/v1/users/{id}", user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON));

        // Asserting the response status and content
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(user.getId().toString())))
                .andExpect(jsonPath("$.userName", is(user.getUserName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.fullName", is(user.getFullName())))
                .andExpect(jsonPath("$.phoneNumber", is(user.getPhoneNumber())))
                .andExpect(result -> compareOffsetDateTime(result, "createdAt", user.getCreatedAt()))
                .andExpect(result -> compareOffsetDateTime(result, "updatedAt", user.getUpdatedAt()));

    }


    @Test
    @Order(2)
    public void testFindUsersByNameContainingEndpoint() throws Exception {

        UserDto user2 = new UserDto(
                UUID.fromString("5588e9b3-1205-48ca-969f-e61d5c6856e8"),
                "test_user_2",
                "test@email.com",
                "John Wick",
                "099428284",
                OffsetDateTime.of(2024, 8, 30, 2, 30, 0, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2024, 8, 30, 3, 40, 0, 0, ZoneOffset.UTC)
        );
        // Mocking the service layer response
        given(userService.findUsersByNameContaining("John")).willReturn(List.of(user, user2));

        // Performing the GET request
        ResultActions response = mockMvc.perform(get("/v1/users?searchTerm=John", user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON));

        // Asserting the response status and content
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id", is(user.getId().toString())))
                .andExpect(jsonPath("$[1].id", is(user2.getId().toString())));
    }

    private void compareOffsetDateTime(MvcResult result, String fieldName, OffsetDateTime expectedDate) throws UnsupportedEncodingException {
        String actualDate = JsonPath.parse(result.getResponse().getContentAsString()).read("$.%s".formatted(fieldName));
        OffsetDateTime actual = OffsetDateTime.parse(actualDate);
        assertThat(actual, is(expectedDate));
    }
}
