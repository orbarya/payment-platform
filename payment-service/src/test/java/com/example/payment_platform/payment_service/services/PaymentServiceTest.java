package com.example.payment_platform.payment_service.services;

import com.example.payment_platform.common.dto.PaymentDto;
import com.example.payment_platform.common.repository.PaymentMethodRepository;
import com.example.payment_platform.common.repository.UserRepository;
import com.example.payment_platform.payment_service.configuration.properties.PaymentsKafkaProducerProperties;
import com.example.payment_platform.payment_service.dto.UserDto;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    @Mock
    private KafkaTemplate<String, PaymentDto> kafkaTemplate;

    @Mock
    private PaymentsKafkaProducerProperties paymentsKafkaProducerProperties;

    private PaymentDto payment;

    @BeforeEach
    public void setup() {
        payment = new PaymentDto(null,
                "f24fa9ba-072f-461c-80ee-964eb93b73dd",
                "bf917534-801a-4aa5-b743-2fa6704e84d7",
                "03fb42bd-447b-4fd6-9deb-d6adf66a07f4",
                100.0,
                "USD",
                OffsetDateTime.of(2024,8,31,15,38,0,0, ZoneOffset.UTC),
                OffsetDateTime.of(2024,8,31,15,38,0,0, ZoneOffset.UTC)
        );
    }

    @Test
    public void testCreate_success() {
        ProducerRecord<String, PaymentDto> producerRecord = new ProducerRecord<>(
                "topic",
                "key",
                payment
        );
        RecordMetadata recordMetadata = new RecordMetadata(
                new TopicPartition("topic", 0),  // topic partition
                0,
                0,
                System.currentTimeMillis(),  // timestamp
                0,
                0
        );
        given(userRepository.existsById(any())).willReturn(true);
        given(paymentMethodRepository.existsByIdAndUser_Id(any(), any())).willReturn(true);
        given(kafkaTemplate.send(any(), any())).willReturn(CompletableFuture.completedFuture(new SendResult<>(producerRecord, recordMetadata)));
        PaymentDto actualPayment = paymentService.create(payment);
        assertThat(actualPayment, is(equalTo(payment)));
    }
}
