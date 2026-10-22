package com.raghul.sdet;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Reporter;

public class CalculatorTngTest 
{
	Calculator calc;
	
	@BeforeMethod(alwaysRun = true)
	public void setUp()
	{
		System.out.println("Setup : new Calculator");
		calc = new Calculator();
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		System.out.println("Cleanup : done");
	}
	
	@Test(description = "Add Two Numbers",groups = {"smoke"})
	public void testAdd()
	{
		Reporter.log("Add Two Numbers: checking 3 + 7 = 10");
		Assert.assertEquals(calc.add(3, 7), 10);
		
	}
	
	@Test(groups = {"regression"})
	public void testSub()
	{
		Assert.assertEquals(calc.sub(550, 500), 50);
	}
	
	@Test(groups = {"smoke","regression"})
	public void testMul()
	{
		Assert.assertEquals(calc.mul(10, 10), 100);
	}
	
	@Test(groups = {"smoke"})
	public void testDiv()
	{
		Assert.assertEquals(calc.div(20, 5), 4);
	}
	
	@Test(groups = {"regression"})
	public void testNotDivisibleByZero()
	{
		Assert.assertThrows(ArithmeticException.class, ()->calc.div(7, 0) );
	}
}
