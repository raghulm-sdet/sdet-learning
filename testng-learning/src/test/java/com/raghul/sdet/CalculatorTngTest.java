package com.raghul.sdet;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Reporter;

public class CalculatorTngTest 
{
	Calculator calc;
	
	@BeforeMethod
	public void setUp()
	{
		System.out.println("Setup : new Calculator");
		calc = new Calculator();
	}
	
	@AfterMethod
	public void tearDown()
	{
		System.out.println("Cleanup : done");
	}
	
	@Test(description = "addsTwoNumbers")
	public void testAdd()
	{
		Reporter.log("Add Two Numbers: checking 3 + 7 = 10");
		Assert.assertEquals(calc.add(3, 7), 10);
		
	}
	
	@Test
	public void testSub()
	{
		Assert.assertEquals(calc.sub(550, 500), 50);
	}
	
	@Test
	public void testMul()
	{
		Assert.assertEquals(calc.mul(10, 10), 100);
	}
	
	@Test
	public void testDiv()
	{
		Assert.assertEquals(calc.div(20, 5), 4);
	}
	
	@Test
	public void testNotDivisibleByZero()
	{
		Assert.assertThrows(ArithmeticException.class, ()->calc.div(7, 0) );
	}
}
