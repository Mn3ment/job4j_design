package ru.job4j.store;

import org.junit.Test;
import ru.job4j.generics.store.Role;
import ru.job4j.generics.store.RoleStore;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;


public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRoleIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Admin"));
    }

    @Test
    public void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindUsernameIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.add(new Role("1", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("User"));
    }

    @Test
    public void whenReplaceThenRoleIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.replace("1", new Role("1", "User"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("User"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.replace("5", new Role("1", "User"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Admin"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteUserThenUsernameIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole(), is("User"));
    }
}