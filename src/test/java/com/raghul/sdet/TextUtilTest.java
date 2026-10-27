package com.raghul.sdet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

public class TextUtilTest 
{
	@Test
	void returnsRequestedLength()
	{
		assertEquals(6, TextUtil.getRandomText(6).length());
	}
	
	@Test
	void returnsNonNull()
	{
		assertNotNull(TextUtil.getRandomText(6));
	}
	
	@Test
	void returnsDigitOnly()
	{
		assertTrue(TextUtil.getRandomText(20).matches("[0-9]+"));
	}
	
	@Test
	void worksForDifferentLengths()
	{
		int[] lengths = {1, 10, 50};
		for (int len : lengths)
		{
			assertEquals(len, TextUtil.getRandomText(len).length());
		}
	}

	@Test
	void zeroReturnsEmptyString()
	{
		assertEquals("", TextUtil.getRandomText(0));
	}

	@Test
	void negativeIsRejected()
	{
		assertThrows(IllegalArgumentException.class, () -> TextUtil.getRandomText(-1));
	}

	@Test
	void producesDifferentValues()
	{
		Set<String> seen = new HashSet<>();
		for (int i = 0; i < 50; i++)
		{
			seen.add(TextUtil.getRandomText(10));
		}
		assertTrue(seen.size() > 1);
	}
}
