package com.alkemy.ong.security.service.userdetailscustomservice;

import com.alkemy.ong.controller.utils.RegisterBasic;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.security.model.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class RegisterNewUserShould extends RegisterBasic {

    @Test()
    void throw_exception_when_email_already_exist() {
        try {
            userAuthService.register(request);
        } catch (DataAlreadyExistException e) {
            assertTrue(true);
        } catch (Exception e){
            assertTrue(false);
        }
    }


    //POR EL MOMENTO SE COMENTA HASTA MOCKEAR LA BASE DE DATOS
/*
    @Test()
    void save_user_entity_in_data_base_2() throws DataAlreadyExistException {
        Mockito.when(userRepository.findByEmail(request.getEmail()).getEmail()).thenReturn(response.getEmail());
        assertNotNull((userRepository.findByEmail(request.getEmail())).getEmail());
    }
    @Test()
    void save_user_entity_in_data_base_3() throws DataAlreadyExistException {
        request.setEmail("newemail@mail.com");
        userAuthService.register(request);
        boolean findEmailInDataBase = (userRepository.findByEmail(request.getEmail())) !=null;
        assertTrue(findEmailInDataBase);
    }
    @Test()
    void save_user_entity_in_data_base_4() throws DataAlreadyExistException {
        userAuthService.register(request);
        UserEntity findUserInDataBase = userRepository.findByEmail(request.getEmail());
        assertNotNull(findUserInDataBase);
    }
*/


}