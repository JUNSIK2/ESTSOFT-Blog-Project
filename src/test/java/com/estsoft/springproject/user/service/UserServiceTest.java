package com.estsoft.springproject.user.service;


import static org.mockito.Mockito.*;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.estsoft.springproject.user.domain.dto.AddUserRequest;
import com.estsoft.springproject.user.domain.entity.Users;
import com.estsoft.springproject.user.repository.UserRepository;

class UserServiceTest {
	@InjectMocks
	UserService service;

	@Mock
	UserRepository repository;

	@Spy
	BCryptPasswordEncoder encoder;

	@BeforeEach
	void setUp() {
	}

	@Test
	@Disabled
	void testSave() {
		// given
		String email = "mock_email";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = encoder.encode("mock_password");
		AddUserRequest request = new AddUserRequest(email, password);

		// userRepository.save -> stub
		Mockito.doReturn(new Users(email, password))
			.when(repository).save(any(Users.class));

		// when
		Users users = service.save(request);

		// then
		MatcherAssert.assertThat(users.getEmail(), Matchers.is(email));

		verify(repository, times(1)).save(any());
		verify(encoder, times(2)).encode(any());
	}
}