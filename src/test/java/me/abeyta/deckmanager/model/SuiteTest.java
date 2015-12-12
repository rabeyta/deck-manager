package me.abeyta.deckmanager.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SuiteTest {

	@Test
	public void valueOfTesting() {//jacoco
		Suite suite = Suite.valueOf("DIAMONDS");
		
		assertEquals(Suite.DIAMONDS, suite);
	}
	
}
