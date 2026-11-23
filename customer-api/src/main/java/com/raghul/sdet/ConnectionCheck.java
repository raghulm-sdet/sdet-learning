package com.raghul.sdet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ConnectionCheck implements CommandLineRunner
{
	private final JdbcTemplate jdbc;

	public ConnectionCheck(JdbcTemplate jdbc)
	{
		this.jdbc = jdbc;
	}

	@Override
	public void run(String... args)
	{
		Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM customers", Integer.class);
		System.out.println(">>> DB CONNECTED. customers rows = " + count);
	}
}