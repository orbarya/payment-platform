package com.example.payment_platform.payment_service.dto;

import com.example.payment_platform.payment_service.controller.model.UserPaymentMethodResponse;
import org.modelmapper.PropertyMap;

public class UserPaymentMethodMapping extends PropertyMap<UserPaymentMethodDto, UserPaymentMethodResponse> {
    @Override
    protected void configure() {
        using(ctx -> ((String) ctx.getSource()).replaceAll(".(?=.{4})", "*"))
                .map(source.getAccountNumber(), destination.getAccountNumber());
    }
}
