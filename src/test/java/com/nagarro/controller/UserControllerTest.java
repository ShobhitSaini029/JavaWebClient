package com.nagarro.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.nagarro.apiData.ApiData;
import com.nagarro.entities.UserEntity;
import com.nagarro.service.GenApiSerive;
import com.nagarro.service.SortingService;
import com.nagarro.service.UserService;
import com.nagarro.service.ValidateService;
import com.nagarro.validation.Validator;
import com.nagarro.validation.ValidatorFactory;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private ValidateService validateService;

    @Mock
    private SortingService sortingService;

    @Mock
    private GenApiSerive genApiService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testCreateUser() throws Exception {
    	
        // Given
        int size = 3;
        List<UserEntity> mockedUsers = Arrays.asList(new UserEntity(), new UserEntity(), new UserEntity());
        when(userService.saveUser(size)).thenReturn(Mono.just(mockedUsers));

        // When and Then
        mockMvc.perform(post("/user").param("size", String.valueOf(size))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUsers() throws Exception {
        // Given
        String sortType = "age";
        String sortOrder = "even";
        String limit = "10";
        String offset = "0";

        List<UserEntity> mockedUsers = Arrays.asList(new UserEntity(), new UserEntity(), new UserEntity());
        when(userService.getUsers(Integer.parseInt(limit), Integer.parseInt(offset))).thenReturn(mockedUsers);
        when(userService.getUserCount()).thenReturn(15);

        Validator sortTypeValidator = ValidatorFactory.createValidator(sortType);
        Validator sortOrderValidator = ValidatorFactory.createValidator(sortOrder);
        Validator limitValidator = ValidatorFactory.createValidator(limit);
        Validator offsetValidator = ValidatorFactory.createValidator(offset);

        // When and Then
        mockMvc.perform(get("/user").param("sortType", sortType)
                .param("sortOrder", sortOrder)
                .param("limit", limit)
                .param("offset", offset)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
