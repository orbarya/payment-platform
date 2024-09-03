package com.example.payment_platform.payment_service.services;

import com.example.payment_platform.common.repository.PaymentMethodRepository;
import com.example.payment_platform.common.model.UserPaymentMethod;
import com.example.payment_platform.payment_service.dto.UserPaymentMethodDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserPaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final ModelMapper modelMapper;

    public UserPaymentMethodService(PaymentMethodRepository paymentMethodRepository, ModelMapper modelMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserPaymentMethodDto> getUserPaymentMethods(String userId) {

        List<UserPaymentMethod> paymentMethods = paymentMethodRepository.findByUserId(UUID.fromString(userId));
        return paymentMethods.stream().map(paymentMethod -> modelMapper.map(paymentMethod, UserPaymentMethodDto.class)).toList();
    }
}
