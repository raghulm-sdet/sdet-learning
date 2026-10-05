package com.raghul.sdet;

public class BankAccount implements Payable
{
	private double balance = 0;

	public void deposit(double amount)
	{
		balance = balance + amount;
	}

	public void withdraw(double amount)
	{
		if(amount > balance)
		{
			System.out.println("Not enough money");
		}
		else
		{
			balance = balance - amount;
		}
	}

	public double getBalance()
	{
		return balance;
	}

	@Override
	public void pay(double amount)
	{
		withdraw(amount);
	}

	public static void main(String[] args)
	{
		BankAccount acc = new BankAccount();
		acc.deposit(1000);
		acc.withdraw(300);
		acc.withdraw(5000);
		acc.pay(100);
		System.out.println(acc.getBalance());
	}
}