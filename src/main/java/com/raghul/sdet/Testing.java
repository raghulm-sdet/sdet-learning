package com.raghul.sdet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

interface Printable
{
	void printDetails();
}

class Person
{
	private String name;
	
	public Person(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}

class Student extends Person implements Printable
{
	private int marks;
	
	public Student(String name, int marks)
	{
		super(name);
		this.marks = marks;
	}
	
	public int getMarks()
	{
		return marks;
	}
	
	@Override
	public void printDetails()
	{
		System.out.println(getName()+ " - " +marks);
	}
}

public class Testing 
{
	public static void main(String[] args)
	{
		ArrayList<Student> students = new ArrayList<>();
		
		students.add(new Student("Raghul", 86));
		students.add(new Student("Naveen", 60));
		students.add(new Student("Manoj", 90));
		
		HashSet<String> names = new HashSet<>();
		
		for(Student student : students)
		{
			names.add(student.getName());
		}
		
		System.out.println("Unique names : "+names);
		
		HashMap<String, Integer> marksMap = new HashMap<>();
		
		for(Student student : students)
		{
			marksMap.put(student.getName(), student.getMarks());
		}
		
		System.out.println("Student marks : "+marksMap);
		
		Printable printable = students.get(0);
		printable.printDetails();
		
		List<String> toppers = students.stream()
				.filter(s -> s.getMarks() >= 85)
				.map(s -> s.getName().toUpperCase())
				.collect(Collectors.toList());
		
		System.out.println("Students scoring 85 or above - "+toppers);
		
		students.forEach(Student::printDetails);
		
	}
}
