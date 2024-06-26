package com.example.UserService.controller;

import com.example.UserService.dto.UserDto;
import com.example.UserService.entity.User;
import com.example.UserService.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

   

    @PostMapping()
    public ResponseEntity<UserDto>  createUser(@RequestBody @Valid UserDto userDto){

     UserDto savedUser =userService.createUser(userDto);
     return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id){
       UserDto userId = userService.userById(id);

       return new ResponseEntity<>(userId,HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> findAllUsers(){
        List<UserDto> users = userService.getAllUser();

        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser( @PathVariable Long id,@RequestBody @Valid UserDto userDto ){
        userDto.setId(id);
       UserDto updatedUser = userService.updateUser(userDto);
       return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
         userService.deleteUserById(id);
         return new ResponseEntity<>("user deleted",HttpStatus.OK);
    }
}
