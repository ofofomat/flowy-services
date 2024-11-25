package br.edu.catolicasc.flowyservices.service;

import br.edu.catolicasc.flowyservices.entity.FlowyUser;
import br.edu.catolicasc.flowyservices.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUser() {
        FlowyUser user1 = new FlowyUser();
        FlowyUser user2 = new FlowyUser();
        List<FlowyUser> userList = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<FlowyUser> result = userService.getAllUser();
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testSaveUser() {
        FlowyUser user = new FlowyUser();

        when(userRepository.save(user)).thenReturn(user);

        FlowyUser result = userService.saveUser(user);
        assertNotNull(result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testGetUserByUserName() {
        FlowyUser user = new FlowyUser();
        user.setUserName("testUser");

        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(user));

        Optional<FlowyUser> result = userService.getUserByUserName("testUser");
        assertTrue(result.isPresent());
        assertEquals("testUser", result.get().getUserName());
        verify(userRepository, times(1)).findByUserName("testUser");
    }
}