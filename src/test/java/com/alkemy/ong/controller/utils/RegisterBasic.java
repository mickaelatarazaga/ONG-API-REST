package com.alkemy.ong.controller.utils;


import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.security.model.UserEntity;
import com.alkemy.ong.security.dto.UserLoginRequest;
import com.alkemy.ong.security.dto.UserRegisterRequest;
import com.alkemy.ong.security.dto.UserRegisterResponse;
import com.alkemy.ong.security.enums.RoleEnum;
import com.alkemy.ong.security.service.UserDetailsCustomService;
import com.alkemy.ong.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.security.service.JwtUtils;
import com.alkemy.ong.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthBasic {
   
    @MockBean
    protected UserDetailsCustomService userAuthService;
    @Autowired
    protected JwtUtils jwtUtil;
    @Autowired
    protected WebApplicationContext context;
    @Autowired
    protected RoleService roleService;
    @Autowired
    protected MessageSource messageSource;
    @Autowired
    protected UserService userService;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @MockBean
    protected UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
    protected UserEntity user;
    protected UserRegisterResponse response;
    protected UserRegisterRequest request;
    protected UserLoginRequest loginRequest;
    protected String jwt;

    @BeforeEach
    protected void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        //ROLES CONFIG
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleService.findByName(RoleEnum.USER.getRoleName()));
        //LOGIN REQUEST
        loginRequest = new UserLoginRequest();
        loginRequest.setEmail("tarazaga.mickaela@gmail.com");
        loginRequest.setPassword("password123");
        //USER ENTITY
        user = new UserEntity();
        user.setId(1L);
        user.setEmail("leonardo@gmail.com");
        user.setFirstName("Leonardo");
        user.setLastName("Tarazaga");
        user.setPassword("123456");
        user.setPhoto("imagen/1.jpg");
        user.setRoles(roleList);
        jwt = jwtUtil.generateToken(user);
        //REGISTER RESPONSE
        response = new UserRegisterResponse();
        response.setEmail("tarazaga.mickaela@gmail.com");
        response.setFirstName("Mickaela");
        response.setLastName("Tarazaga");
        response.setId(1L);
        response.setJwt("token");
        //REGISTER REQUEST
        request = new UserRegisterRequest();
        request.setEmail("tarazaga.mickaela@gmail.com");
        request.setFirstName("Mickaela");
        request.setLastName("Tarazaga");
        request.setPassword("password123");
        request.setPhoto(null);
    }
}
