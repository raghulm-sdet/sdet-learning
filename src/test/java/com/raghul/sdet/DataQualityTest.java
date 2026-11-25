package com.raghul.sdet;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class DataQualityTest
{
	private static final String URL =
		"jdbc:mysql://localhost:3306/sdet_practice?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

	private int countRows(String sql) throws Exception
	{
		String password = System.getenv("SDET_DB_PASSWORD");
		assertNotNull(password, "Set SDET_DB_PASSWORD in the run configuration");

		try (Connection con = DriverManager.getConnection(URL, "root", password);
			 Statement st = con.createStatement();
			 ResultSet rs = st.executeQuery(sql))
		{
			rs.next();
			return rs.getInt(1);
		}
	}

	@Test
	void noOrphanOrders() throws Exception
	{
		int orphans = countRows(
			"SELECT COUNT(*) FROM orders o "
			+ "LEFT JOIN customers c ON o.customer_id = c.id "
			+ "WHERE c.id IS NULL");

		assertEquals(0, orphans, "Orders with no matching customer");
	}
	
	@Test
	void noDuplicateCustomers() throws Exception
	{
		int duplicates = countRows(
			"SELECT COUNT(*) FROM ("
			+ "SELECT name, email FROM customers "
			+ "GROUP BY name, email HAVING COUNT(*) > 1) d");

		assertEquals(0, duplicates, "Duplicate customer name and email pairs");
	}

	@Test
	void noNegativeOrZeroAmounts() throws Exception
	{
		int bad = countRows(
			"SELECT COUNT(*) FROM orders WHERE amount <= 0");

		assertEquals(0, bad, "Orders with an amount of 0 or less");
	}
	
	@Test
	void noDuplicateOrders() throws Exception
	{
		int duplicates = countRows(
			"SELECT COUNT(*) FROM ("
			+ "SELECT customer_id, amount, status FROM orders "
			+ "GROUP BY customer_id, amount, status HAVING COUNT(*) > 1) d");

		assertEquals(0, duplicates, "Duplicate orders with the same customer, amount and status");
	}

	@Test
	void noCustomersWithoutEmail() throws Exception
	{
		int missing = countRows(
			"SELECT COUNT(*) FROM customers WHERE email IS NULL");

		assertEquals(0, missing, "Customers with no email");
	}
	
	@Test
	void noDuplicateApiCustomers() throws Exception
	{
		int duplicates = countRows(
			"SELECT COUNT(*) FROM ("
			+ "SELECT name, email FROM api_customers "
			+ "GROUP BY name, email HAVING COUNT(*) > 1) d");

		assertEquals(0, duplicates, "Duplicate customers created through the API");
	}
}