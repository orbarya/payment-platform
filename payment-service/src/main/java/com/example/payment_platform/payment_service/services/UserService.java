package com.example.payment_platform.payment_service.services;

import com.example.payment_platform.common.repository.UserRepository;
import com.example.payment_platform.common.model.User;
import com.example.payment_platform.payment_service.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDto get(String id) {
        Optional<User> userO = userRepository.findById(UUID.fromString(id));
        return userO.map(user -> modelMapper.map(user, UserDto.class)).orElse(null);
    }

    public List<UserDto> findUsersByNameContaining(String searchTerm) {
        List<User> users = userRepository.findByFullNameContaining(searchTerm);
        return users.stream().map(u -> modelMapper.map(u, UserDto.class)).toList();
    }
}
