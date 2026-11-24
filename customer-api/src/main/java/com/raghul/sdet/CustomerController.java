package com.raghul.sdet;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
	private final JdbcTemplate jdbc;

	public CustomerController(JdbcTemplate jdbc)
	{
		this.jdbc = jdbc;
	}

	@PostMapping
	public ResponseEntity<Customer> create(@RequestBody Customer c)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(con -> {
			PreparedStatement ps = con.prepareStatement(
				"INSERT INTO api_customers (name, email) VALUES (?, ?)",
				Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			return ps;
		}, keyHolder);

		c.setId(keyHolder.getKey().intValue());
		return ResponseEntity.status(HttpStatus.CREATED).body(c);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getById(@PathVariable("id") int id)
	{
		List<Customer> found = jdbc.query(
			"SELECT id, name, email FROM api_customers WHERE id = ?",
			new Object[] { id },
			(rs, i) -> {
				Customer c = new Customer();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setEmail(rs.getString("email"));
				return c;
			});

		if (found.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(found.get(0));
	}
}