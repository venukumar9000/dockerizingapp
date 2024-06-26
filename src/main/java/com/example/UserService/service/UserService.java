package com.example.UserService.service;

import com.example.UserService.dto.UserDto;
import com.example.UserService.entity.User;
import com.example.UserService.exception.EmailAlreadyExitsException;
import com.example.UserService.exception.ResourceNotFound;
import com.example.UserService.mapper.UserMapper;
import com.example.UserService.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public UserDto createUser(UserDto userDto){

        Optional<User> user1 = userRepository.findByEmail(userDto.getEmail());
        if(user1.isPresent()){
            throw new EmailAlreadyExitsException("User already exits in database");

        }
//        User user = UserMapper.mapToUser(userDto);
        User user = modelMapper.map(userDto,User.class);
        User savedUser = userRepository.save(user);
//        UserDto userDto1 = UserMapper.matoToUserDto(savedUser);
        UserDto userDto1 = modelMapper.map(savedUser,UserDto.class);
        return userDto1;
    }
    public UserDto userById(Long id){
        User userId = userRepository.findById(id).orElseThrow(()->new ResourceNotFound("User","id",id));
//        User user = userId.get();
//        return UserMapper.matoToUserDto(user);
        return modelMapper.map(userId.getId(),UserDto.class);
    }
    public List<UserDto> getAllUser(){
        List<User> users =userRepository.findAll();
//        return user.stream().map(UserMapper::matoToUserDto).collect(Collectors.toList());
        return users.stream().map((user)->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }
    public UserDto updateUser(UserDto userDto){
        User existingUser=userRepository.findById(userDto.getId()).orElseThrow(()->new ResourceNotFound("user","id", userDto.getId()));
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
       User updatedUser= userRepository.save(existingUser);
//       return UserMapper.matoToUserDto(updatedUser);
        return modelMapper.map(updatedUser,UserDto.class);

    }
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
}
