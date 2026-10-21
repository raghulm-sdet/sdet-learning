package com.raghul.sdet;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class BankAccountTngTest 
{
	BankAccount bc;
	
	@BeforeMethod
	public void setUp()
	{
		System.out.println("Setup : New BankAccount");
		bc = new BankAccount();
	}
	
	@AfterMethod
	public void tearDown()
	{
		System.out.println("Cleanup : done");
	}
	
	@Test
	public void testNewAccountBalance()
	{
		Assert.assertEquals(bc.getBalance(), 0.0, 0.001);
	}
	
	@Test
	public void testDeposit()
	{
		bc.deposit(2500);
		Assert.assertEquals(bc.getBalance(), 2500, 0.001);
	}
	
	@Test
	public void testWithdraw()
	{
		bc.deposit(1000);
		bc.withdraw(250);
		Assert.assertEquals(bc.getBalance(), 750, 0.001);
	}
	
	@Test
	public void testWithdrawMoreThanBalance()
	{
		bc.deposit(1000);
		bc.withdraw(2500);
		Assert.assertEquals(bc.getBalance(), 1000, 0.001);
	}
	
	@Test
	public void testDepositNegativeIsRefused()
	{
		IllegalArgumentException ex = Assert.expectThrows(
				IllegalArgumentException.class, () -> bc.deposit(-100));
		Assert.assertEquals(ex.getMessage(), "Deposit Amount must be Positive!");
	}
	
	@Test
	public void testWithdrawNegativeIsRefused()
	{
		bc.deposit(1000);
		IllegalArgumentException ex = Assert.expectThrows(
				IllegalArgumentException.class, () -> bc.withdraw(-100));
		Assert.assertEquals(ex.getMessage(), "Withdraw Amount must be Positive!");
	}
	
	@Test
	public void testPayFromBalance()
	{
		bc.deposit(1000);
		bc.pay(220.50);
		Assert.assertEquals(bc.getBalance(), 779.50, 0.001);
	}
	
	@Test
	public void testPayNegativeIsRefused()
	{
		Assert.assertThrows(IllegalArgumentException.class, () -> bc.pay(-100));
	}
	
	@Test
	public void testDepositZeroIsRefused()
	{
		IllegalArgumentException ex = Assert.expectThrows(
				IllegalArgumentException.class, () -> bc.deposit(0));
		Assert.assertEquals(ex.getMessage(), "Deposit Amount must be Positive!");
	}
	
	@DataProvider(name = "addAmount")
	public Object[][] addAmount()
	{
		return new Object[][]
		{
			{1000.0, 300.0, 700.0},
			{100.0, 500.0, 100.0},
			{500.0, 500.0, 0.0},
			{250.50, 50.25, 200.25}
		};
	}
	
	@Test(dataProvider = "addAmount")
	public void testProvider(double start, double withdraw, double expectedBalance)
	{
		bc.deposit(start);
		bc.withdraw(withdraw);
		Assert.assertEquals(bc.getBalance(), expectedBalance, 0.001);
	}
	
	@DataProvider(name = "badAmounts")
	public Object[][] badAmounts()
	{
		return new Object[][] 
		{ 
			{-500.0}, 
			{-0.01}, 
			{0.0} 
		};
	}

	@Test(dataProvider = "badAmounts")
	public void testDepositBadAmountIsRejected(double amount)
	{
		Assert.assertThrows(IllegalArgumentException.class, () -> bc.deposit(amount));
	}
}
