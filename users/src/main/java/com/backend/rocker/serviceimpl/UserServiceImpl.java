package com.backend.rocker.serviceimpl;

import com.backend.rocker.Utils.UserDTOMapper;
import com.backend.rocker.dtos.UserDTO;
import com.backend.rocker.exceptions.DuplicateRecordFound;
import com.backend.rocker.exceptions.UserNotFoundException;
import com.backend.rocker.model.User;
import com.backend.rocker.repository.UserRepository;
import com.backend.rocker.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO registerUser(UserDTO requestUser) {
        User duplicate = userRepository.findFirstByNameOrPhoneNumberOrEmail(
                requestUser.getUsername(),
                requestUser.getPhoneNumber(),
                requestUser.getEmail());
        if (duplicate != null) {
            if (duplicate.getUsername().equalsIgnoreCase(requestUser.getUsername())) {
                throw new DuplicateRecordFound("Username Already Exists");
            }
            if (duplicate.getPhoneNumber().equals(requestUser.getPhoneNumber())) {
                throw new DuplicateRecordFound("Phone Number Already Registered");
            }
            if (duplicate.getEmail().equalsIgnoreCase(requestUser.getEmail())) {
                throw new DuplicateRecordFound("Email Already Registered");
            }
        }
        User user = UserDTOMapper.convertDtoToUser(requestUser);
        User saved = userRepository.save(user);
        return UserDTOMapper.convertUserToDto(saved);
    }



    @Override
    public UserDTO getUserByUserId(Long userId) {
            return findUser(() -> userRepository.findById(userId),
                    "User not found with userId: " + userId);
    }

    @Override
    public UserDTO getUserByPhone(String phoneNumber) {
        return findUser(() -> userRepository.findByPhoneNumber(phoneNumber),
                "User not found with phone number: " + phoneNumber);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return findUser(() -> userRepository.findByEmail(email),
                "User not found with email: " + email);
    }

    @Override
    public UserDTO updateUser(UserDTO requestUser) {
        User user = UserDTOMapper.convertDtoToUser(requestUser);
        return UserDTOMapper.convertUserToDto(userRepository.save(user));
    }

    @Override
    public UserDTO deleteUserById(Long userId) {
        return null;
    }

    @Override
    public UserDTO deleteUserByPhone(String phoneNumber) {
        return null;
    }

    @Override
    public UserDTO deleteUserByEmail(String email) {
        return null;
    }

    public UserDTO findUser(Supplier<Optional<User>> finder, String errorMsg) {
        User user = finder.get()
                .orElseThrow(() -> new UserNotFoundException(errorMsg));
        return UserDTOMapper.convertUserToDto(user);
    }

}
