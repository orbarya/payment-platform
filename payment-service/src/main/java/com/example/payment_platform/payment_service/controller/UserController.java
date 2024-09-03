package com.example.payment_platform.payment_service.controller;

import com.example.payment_platform.payment_service.controller.model.GetUserResponse;
import com.example.payment_platform.payment_service.controller.model.PayeeResponse;
import com.example.payment_platform.payment_service.dto.UserDto;
import com.example.payment_platform.payment_service.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    ResponseEntity<GetUserResponse> getUser(@PathVariable String id) {
        UserDto user = userService.get(id);
        GetUserResponse getUserResponse = modelMapper.map(user, GetUserResponse.class);

        if (user != null) {
            return ResponseEntity.ok(getUserResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
        ResponseEntity<List<PayeeResponse>> findUsersByNameContaining(@RequestParam(name="searchTerm") String fullNameSearchTerm) {
        List<UserDto> users = userService.findUsersByNameContaining(fullNameSearchTerm);
        List<PayeeResponse> payees = users.stream().map(user -> modelMapper.map(user, PayeeResponse.class)).toList();
        return ResponseEntity.ok(payees);
    }
}
