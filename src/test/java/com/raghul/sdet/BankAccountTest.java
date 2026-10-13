package com.raghul.sdet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BankAccountTest 
{
	BankAccount acc = new BankAccount();
	
	@Test
	void testNotNull()
	{
		assertNotNull(acc);
	}
	
	@Test
	void testNewAccCheckEmpty()
	{
		assertFalse(acc.getBalance() > 0);
	}
	
	@Test
	void testDeposit()
	{
		acc.deposit(1000);
		assertTrue(acc.getBalance() > 0);
	}
	
	@Test
	void testWithdraw()
	{
		acc.deposit(1000);
		acc.withdraw(300);
		
		assertEquals(700.0, acc.getBalance(), 0.001);
	}
	
	@Test
	void testRefuseToWithdrawal()
	{
		acc.deposit(500);
		acc.withdraw(5000);
		
		assertEquals(500.0, acc.getBalance(), 0.001);
	}
	
	@Test
	void testCustomMessage()
	{
		acc.deposit(1000);
		acc.withdraw(300);
		
		assertEquals(700.0, acc.getBalance(), 0.001, "Balance should be 700 after withdrawing 300");
	}
}
