package net.journalApp.Repository;

import net.journalApp.entity.User;
import net.journalApp.repository.UserRepositoryImpl;
import net.journalApp.service.UserArgumentsProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryImplTests {

    private UserRepositoryImpl userRepository;
    @Test
   public void testSaveNewUser(){
        Assertions.assertNotNull(userRepository.getUserForSA());
    }
}
