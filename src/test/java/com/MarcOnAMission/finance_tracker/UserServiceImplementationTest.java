package com.MarcOnAMission.finance_tracker;

import com.MarcOnAMission.finance_tracker.CustomExceptions.WeakPasswordException;
import com.MarcOnAMission.finance_tracker.DataTransferObjects.UserDataTransferObject;
import com.MarcOnAMission.finance_tracker.Mappers.ApplicationUserDTOMapper;
import com.MarcOnAMission.finance_tracker.Model.ApplicationUser;
import com.MarcOnAMission.finance_tracker.Repositories.UserRepository;
import com.MarcOnAMission.finance_tracker.ServiceImplementations.UserServiceImp;
import com.MarcOnAMission.finance_tracker.Services.UserService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplementationTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImp userService;

    private UserDataTransferObject userDataTransferObject;
    @BeforeEach
    void setUp(){
        userDataTransferObject = new UserDataTransferObject();
        userDataTransferObject.setPassword("1234");
        userDataTransferObject.setUsername("Tyler Durden");
    }

    @Test
    void shouldCreateUserWhenUserDoesNotExist(){
        //given
        when(userRepository.findByUsername("Tyler Durden")).thenReturn(Optional.empty());
        ApplicationUser savedUser = new ApplicationUser();
        savedUser.setApplicationUserId(1L);
        savedUser.setUsername("Tyler Durden");
        savedUser.setPassword("12345");
        when(userRepository.save(any(ApplicationUser.class))).thenReturn(savedUser);
        //when
        UserDataTransferObject resultDataTransferObject = userService.validateCredentialInputAndCreateApplicationUser(userDataTransferObject);
        //then
        Mockito.verify(userRepository).findByUsername("Tyler Durden");
        Mockito.verify(userRepository.save(ApplicationUserDTOMapper.createAppUserEntity(userDataTransferObject)));
        Mockito.verify(userRepository).save(any(ApplicationUser.class));
        assertEquals(savedUser.getUsername(),resultDataTransferObject.getUsername());
        assertEquals(savedUser.getPassword(),resultDataTransferObject.getPassword());
    }
    @Test
    void shouldThrowExceptionWhenPasswordIsTooShort(){
        UserDataTransferObject weakPasswordDataTransferObject = new UserDataTransferObject("wek","Tyler Durden");
        when(userRepository.findByUsername("Tyler Durden")).thenReturn(Optional.empty());
        assertThrows(WeakPasswordException.class,()->userService.validateCredentialInputAndCreateApplicationUser(weakPasswordDataTransferObject));
        verify(userRepository).findByUsername("Tyler Durden");
    }
}
