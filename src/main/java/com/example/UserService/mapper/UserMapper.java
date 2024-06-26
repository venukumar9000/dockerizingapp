package com.example.UserService.mapper;

import com.example.UserService.dto.UserDto;
import com.example.UserService.entity.User;

public class UserMapper {
    public static User mapToUser(UserDto userDto){
      User user = new User(
              userDto.getId(),
              userDto.getFirstName(),
              userDto.getLastName(),
              userDto.getEmail()
      );
      return user;
  }
  public static  UserDto matoToUserDto(User user){
      UserDto userDto= new UserDto(
              user.getId(),
              user.getFirstName(),
              user.getLastName(),
              user.getEmail()
      );
      return userDto;
  }
















}
