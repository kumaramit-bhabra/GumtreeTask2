package com.gumtree.example02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAdapterTest
{
    UserAdapter userAdapter;

    @BeforeEach
    public void setup()
    {
        userAdapter = new UserAdapterImpl();
    }

    @SuppressWarnings("unchecked")
    @DisplayName("Validating User Id attribute for created user")
    @Test
    public void testUserId()
    {
        // Creating the stub of DbUser Object with the attributes that are required for User
        DbUser dbuser = DbUser.Builder.aDbUser().withId(1).withFirstname("gumtree").withSurname("test").build();
        assertEquals(1, userAdapter.adapt(dbuser).getId());
    }

    @SuppressWarnings("unchecked")
    @DisplayName("Validating full name functionality associated with getFullName() and checking isPrivate attribute")
    @Test
    public void testUserName()
    {
        DbUser dbuser = DbUser.Builder.aDbUser().withId(1).withFirstname("gumtree").withSurname("test").build();
        assertEquals("gumtree test", userAdapter.adapt(dbuser).getFullName());
        assertEquals(false, userAdapter.adapt(dbuser).isPrivate());
    }

    @SuppressWarnings("unchecked")
    @DisplayName("Validating is Private attribute value if DB User is created with this attribute")
    @Test
    public void testIfPrivate()
    {
        DbUser dbuser = DbUser.Builder.aDbUser().withId(1).withFirstname("gumtree").withSurname("test").withIsPrivate(true).build();
        assertEquals(true, userAdapter.adapt(dbuser).isPrivate());
    }
}