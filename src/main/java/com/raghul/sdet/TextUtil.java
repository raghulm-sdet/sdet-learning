package com.raghul.sdet;

import java.util.Random;

public class TextUtil 
{
	private static final Random RANDOM = new Random();
	
	public static String getRandomText(int noOfDigits)
	{
		if (noOfDigits < 0)
		{
			throw new IllegalArgumentException("Number of digits cannot be negative");
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < noOfDigits; i++)
		{
			sb.append(RANDOM.nextInt(10));
		}
		return sb.toString();
	}
}
