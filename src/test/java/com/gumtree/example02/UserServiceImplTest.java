package com.gumtree.example02;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserServiceImplTest
{
    // Creating the mocks for dependencies
    @Mock
    UsersGateway usersGateway;
    @Mock
    UserAdapter userAdapter;

    // Creating the object and respective dependencies
    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @SuppressWarnings("unchecked")
    @DisplayName("Validate user is present")
    @Test
    public void testUserIsPresent()
    {
        DbUser dbuser = DbUser.Builder.aDbUser().withId(1).withFirstname("Gumtree").withSurname("Test").build();
        when(usersGateway.getById(1)).thenReturn(Optional.of(dbuser));
        when(userAdapter.adapt(dbuser)).thenReturn(User.Builder.aUser().withId(1).withFullName("Gumtree Test").build());

        assertEquals(1, userService.getUser(1).get().getId());
        assertEquals("Gumtree Test", userService.getUser(1).get().getFullName());
    }

    @SuppressWarnings("unchecked")
    @Test
    @DisplayName("Validate user is not present")
    public void testUserIsNotPresent()
    {
        when(usersGateway.getById(1)).thenReturn(Optional.ofNullable(null));
        assertEquals(Optional.empty(), userService.getUser(1));
    }
}