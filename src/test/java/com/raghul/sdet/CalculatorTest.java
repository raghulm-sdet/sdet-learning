package com.raghul.sdet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest 
{
	Calculator calc = new Calculator();
	
	@Test
	void AddTwoNumbers()
	{
		assertEquals(5, calc.add(2, 3));
	}
	
	@Test
	void SubTwoNumbers()
	{
		assertEquals(10, calc.sub(15, 5));
	}
	
	@Test
	void MulTwoNumbers()
	{
		assertEquals(12, calc.mul(2, 6));
	}
	
	@Test
	void DivTwoNumbers()
	{
		assertEquals(4, calc.div(12, 3));
	}
	
	@Test
	void divideByZeroThrowsException()
	{
		assertThrows(ArithmeticException.class, () -> calc.div(6, 0));
	}
}
