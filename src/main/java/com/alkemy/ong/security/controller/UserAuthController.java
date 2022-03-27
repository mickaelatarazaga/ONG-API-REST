package com.alkemy.ong.security.controller;

import com.alkemy.ong.dto.*;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.security.dto.UserRegisterRequest;
import com.alkemy.ong.security.dto.UserRegisterResponse;
import com.alkemy.ong.security.mapper.UserMapper;
import com.alkemy.ong.security.model.UserEntity;
import com.alkemy.ong.security.service.JwtUtils;
import com.alkemy.ong.security.service.UserDetailsCustomService;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private UserMapper userMapper;

    //---------------a eliminar-----------------------------------------
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;
    //---------------a eliminar-----------------------------------------

    @GetMapping("/me")
    public ResponseEntity<?> userData(HttpServletRequest request){
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        return ResponseEntity.ok(userMapper.convertUserToDto(userService.loginUser(username)));
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> logIn(@Valid @RequestBody UserDtoCreator userDto){

        UserEntity user = userMapper.UserDtoToEntity(userDto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.loginUser(user));
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest userReq) throws DataAlreadyExistException, IOException {
        return new ResponseEntity<>(userDetailsCustomService.register(userReq), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>>getAllUsersD() {
        return new ResponseEntity<List<UserDto>>(userService.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(userMapper.convertUserToDto(userService.findUserById(id).get()));
    }



    /*devolver este metodo a CategoryController cuando se termine de probar*/
    @PostMapping("/categories")
    public ResponseEntity<Category> addNewCategory(@Valid @RequestBody CategoryDto categoryDto) throws DataAlreadyExistException {

        Category category = categoryService.save(categoryMapper.categoryDto2Entity(categoryDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(category);

    }
}
