package com.raghul.sdet;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

public class DbConnectionTest 
{
	private static final String URL =
			"jdbc:mysql://localhost:3306/sdet_practice?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

		@Test
		void canConnectToPracticeDatabase() throws Exception
		{
			String password = System.getenv("SDET_DB_PASSWORD");
			assertNotNull(password, "Set SDET_DB_PASSWORD in the run configuration");

			try (Connection con = DriverManager.getConnection(URL, "root", password))
			{
				assertFalse(con.isClosed());
			}
		}
}
