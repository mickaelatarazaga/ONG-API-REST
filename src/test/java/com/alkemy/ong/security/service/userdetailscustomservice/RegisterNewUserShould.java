package com.alkemy.ong.security.service.userdetailscustomservice;

import com.alkemy.ong.controller.utils.AuthBasic;
import com.alkemy.ong.exception.DataAlreadyExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class RegisterNewUserShould extends AuthBasic {

    @Test()
    void throw_exception_when_email_already_exist() throws Exception {
        request.setEmail("leonardo@gmail.com");
        Mockito.when(userAuthService.register(request)).thenThrow(new DataAlreadyExistException("The user already esist in our Data Base."));
    }


}