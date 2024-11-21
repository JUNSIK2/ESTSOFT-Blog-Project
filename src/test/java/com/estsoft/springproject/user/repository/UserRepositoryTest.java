package com.estsoft.springproject.user.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.estsoft.springproject.user.domain.entity.Users;

@DataJpaTest
class UserRepositoryTest {
	@Autowired
	private UserRepository repository;

	private static Users getUsers() {
		String email = "test@email.com";
		String password = "test123";
		return new Users(email, password);
	}

	@Test
	public void testFindUser() {
		// given
		Users users = getUsers();

		repository.save(users);

		Users returnUsers = repository.findByEmail(users.getEmail()).orElseThrow();

		assertThat(returnUsers.getEmail(), is(users.getEmail()));
		assertThat(returnUsers.getPassword(), is(users.getPassword()));
	}

	@Test
	public void testSave(){
		repository.save(getUsers());
		repository.save(getUsers());

		List<Users> usersList = repository.findAll();

		assertThat(usersList.size(), is(2));
	}

}
