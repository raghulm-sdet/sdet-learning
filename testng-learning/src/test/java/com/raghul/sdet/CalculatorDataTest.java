package com.raghul.sdet;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorDataTest 
{
	Calculator cl;
	
	@BeforeMethod
	public void setUp()
	{
		cl = new Calculator();
	}
	
	@DataProvider(name = "addData")
	public Object[][] addData()
	{
		return new Object[][]
		{
			{2,3,5},
			{20,10,30},
			{-1,1,0},
			{0,0,0},
			{590,1410,2000},
			{10,-120,-110}
		};
	}
	
	@Test(dataProvider = "addData")
	public void testAdd(int a, int b, int expected)
	{
		Assert.assertEquals(cl.add(a, b), expected);
	}
	
	@DataProvider(name = "subData")
	public Object[][] subData()
	{
		return new Object[][] 
		{
			{100,0,100},
			{3,2,1},
			{10,45,-35},
			{10000,2500,7500}
		};
	}
	
	@Test(dataProvider = "subData")
	public void testSub(int a, int b, int expected)
	{
		Assert.assertEquals(cl.sub(a, b), expected);
	}
	
	@DataProvider(name = "mulData")
	public Object[][] mulData()
	{
		return new Object[][]
		{
			{1,0,0},
			{10,55,550},
			{10,-6,-60},
			{0,0,0}
		};
	}
	
	@Test(dataProvider = "mulData")
	public void testMul(int a, int b, int expected)
	{
		Assert.assertEquals(cl.mul(a, b), expected);
	}
	
	@DataProvider(name = "divData")
	public Object[][] divData()
	{
		return new Object[][]
		{
			{1,1,1},
			{10,2,5},
			{100000,5,20000},
			{55,11,5}
		};
	}
	
	@Test(dataProvider = "divData")
	public void testDiv(int a, int b, int expected)
	{
		Assert.assertEquals(cl.div(a, b), expected, 0.001);
	}
}
