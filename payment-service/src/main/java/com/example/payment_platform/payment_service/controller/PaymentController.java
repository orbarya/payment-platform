package com.example.payment_platform.payment_service.controller;

import com.example.payment_platform.payment_service.controller.model.CreatePaymentRequest;
import com.example.payment_platform.payment_service.controller.model.CreatePaymentResponse;
import com.example.payment_platform.common.dto.PaymentDto;
import com.example.payment_platform.payment_service.services.PaymentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payments")
class PaymentController {
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService, ModelMapper modelMapper) {
        this.paymentService = paymentService;
        this.modelMapper = modelMapper;

    }

    @PostMapping("")
    ResponseEntity<CreatePaymentResponse> create(@Valid @RequestBody CreatePaymentRequest request) {
        PaymentDto paymentDto = modelMapper.map(request, PaymentDto.class);
        PaymentDto payment = paymentService.create(paymentDto);

        CreatePaymentResponse response = modelMapper.map(payment, CreatePaymentResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
