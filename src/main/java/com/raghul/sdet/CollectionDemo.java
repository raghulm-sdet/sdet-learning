package com.raghul.sdet;

import java.util.*;

public class CollectionDemo 
{
	public static void main(String args[])
	{
		ArrayList<Integer> numbers = new ArrayList<>();
	
		numbers.add(5);
		numbers.add(12);
		numbers.add(8);
		numbers.add(20);
		numbers.add(3);
		
		int sum = 0;
		int largest = numbers.get(0);
		
		for(int number : numbers)
		{
			sum = sum + number;
			
			if(number > largest)
			{
				largest = number;
			}
		}
		
		System.out.println("Arraylist:" +numbers);
		System.out.println("Sum:" +sum);
		System.out.println("Largest:" +largest);
		
		
		HashSet<String> names = new HashSet<>();
		names.add("Ravi");
		names.add("Anu");
		names.add("Ravi");
		names.add("Kumar");
		names.add("Anu");
		
		System.out.println("\nHashSet:" +names);
		System.out.println("Size:" +names.size());
		System.out.println("Names that stayed:" +names);
		
		HashMap<String, Integer> students = new HashMap<>();

        students.put("Anu", 85);
        students.put("Ravi", 90);
        students.put("Kumar", 78);

        System.out.println("\nHashMap:");

        for(Map.Entry<String, Integer> entry : students.entrySet()) 
        {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        
        System.out.println("\nAbove 75:");
        for(Map.Entry<String, Integer> entry : students.entrySet())
        {
        	if(entry.getValue() > 75)
        	{
        		System.out.println(entry.getKey() + " -> " + entry.getValue());
        	}
        }
		
	}
}
