package com.raghul.sdet;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaAndStream
{
	public static void main(String[] args)
	{
		List<Integer> numbers = Arrays.asList(5, 12, 8, 20, 3);
		
		List<Integer> result = numbers.stream()
		.filter(n -> n > 5)
		.map(n -> n * 2)
		.collect(Collectors.toList());
		
		System.out.println(result);
	}
}