package com.estsoft.springproject.tdd;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TDD
// 1. 계좌 생성
// 2. 잔금 조회
// 3. 입/출금
public class AccountTest {
	Account account;
	@BeforeEach
	public void setup(){
		account = new Account(10000);
	}

	@Test
	public void testCreate() {
		assertThat(account.getBalance(), is(10000)); // hamcrest로 검증
	}

	@Test
	public void testDeposit() {
		account.deposit(200000);
		assertThat(account.getBalance(), is(210000));
	}

	@Test
	public void testWithdraw(){
		account.withdraw(5000);
		assertThat(account.getBalance(), is(5000));
	}
}
