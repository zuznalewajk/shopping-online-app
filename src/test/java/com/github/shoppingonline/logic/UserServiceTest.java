package com.github.shoppingonline.logic;

import com.github.shoppingonline.exception.NotEnoughProductInStockException;
import com.github.shoppingonline.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Test
    @DisplayName("should save new User")
    void saveUser_UserIsOk_addsNewUserToRepo() {
        // given
        User user = new User();
        String email = "john@gmail.com";
        user.setEmail(email);
        // and
        UserRepository mockUserRepository = mock(UserRepository.class);
        when(mockUserRepository.save(any(User.class))).thenReturn(user);
        // and
        BCryptPasswordEncoder mockBCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        // system under test
        var toTest = new UserService(mockUserRepository, mockBCryptPasswordEncoder);
        // when
        User result = toTest.saveUser(user);
        // then
        assertEquals(email, result.getEmail());
    }
}




