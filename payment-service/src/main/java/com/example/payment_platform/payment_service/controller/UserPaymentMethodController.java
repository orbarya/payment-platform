package com.example.payment_platform.payment_service.controller;

import com.example.payment_platform.payment_service.controller.model.UserPaymentMethodResponse;
import com.example.payment_platform.payment_service.dto.UserPaymentMethodDto;
import com.example.payment_platform.payment_service.services.UserPaymentMethodService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/users/{user_id}/payment-methods")
public class UserPaymentMethodController {
    private final UserPaymentMethodService paymentMethodServiceService;
    private final ModelMapper modelMapper;

    public UserPaymentMethodController(UserPaymentMethodService paymentMethodServiceService,
                                       ModelMapper modelMapper) {
        this.paymentMethodServiceService = paymentMethodServiceService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    ResponseEntity<List<UserPaymentMethodResponse>> getUserPaymentMethods(@PathVariable("user_id") String userId) {

        List<UserPaymentMethodDto> userPaymentMethods = paymentMethodServiceService.getUserPaymentMethods(userId);
        List<UserPaymentMethodResponse> result = userPaymentMethods.stream().map(pm -> modelMapper.map(pm, UserPaymentMethodResponse.class)).toList();

        return ResponseEntity.ok(result);
    }

}
