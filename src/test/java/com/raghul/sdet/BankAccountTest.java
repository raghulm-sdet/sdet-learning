package com.raghul.sdet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BankAccountTest 
{
	BankAccount acc;
	
	@BeforeEach
	void setUp()
	{
		System.out.println("Setup : Creating a fresh account!");
		acc = new BankAccount();
	}
	
	@AfterEach
	void tearDown()
	{
		System.out.println("Cleanup : Test Finished!");
	}
	
	@Test
	void testNotNull()
	{
		assertNotNull(acc);
	}
	
	@Test
	void testNewAccCheckEmpty()
	{
		assertEquals(0.0, acc.getBalance(), 0.001);
	}
	
	@Test
	void testDeposit()
	{
		acc.deposit(1000);
		assertEquals(1000.0, acc.getBalance(), 0.001);
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
