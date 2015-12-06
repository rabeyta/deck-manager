package me.abeyta.deckmanager.config;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import me.abeyta.deckmanager.delegates.shuffle.HandShuffler;
import me.abeyta.deckmanager.delegates.shuffle.Shuffler;
import me.abeyta.deckmanager.delegates.shuffle.SimpleShuffler;

@RunWith(MockitoJUnitRunner.class)
public class DeckManagerConfigTest {

	@InjectMocks
	private DeckManagerConfig config;
	@Mock
	private Environment environment;
	
	@Test
	public void shufflePropNotSetSimpleUsed() {
		Shuffler output = config.shuffler();
		
		assertTrue(output instanceof SimpleShuffler);
	}
	
	@Test
	public void shufflePropSetToSimpleSimpleUsed() {
		when(environment.getProperty("shuffle.technique")).thenReturn("simple");
		
		Shuffler output = config.shuffler();
		
		assertTrue(output instanceof SimpleShuffler);
	}
	
	@Test
	public void shuffleSetHandUsed() {
		when(environment.getProperty("shuffle.technique")).thenReturn("hand");
		
		Shuffler output = config.shuffler();
		
		assertTrue(output instanceof HandShuffler);
	}
}
