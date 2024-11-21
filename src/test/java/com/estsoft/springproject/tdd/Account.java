package com.estsoft.springproject.tdd;

public class Account {
	private int balance;

	public Account(int balance) {
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void deposit(int i) {
		balance += i;
	}

	public void withdraw(int i) {
		balance -= i;
	}
}
