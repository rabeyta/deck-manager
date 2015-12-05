package me.abeyta.deckmanager.controllers;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import me.abeyta.deckmanager.delegates.DeckManager;
import me.abeyta.deckmanager.model.Deck;

@RunWith(MockitoJUnitRunner.class)
public class DeckControllerTest {

	@InjectMocks
	private DeckController controller;
	
	@Mock
	DeckManager manager;
	
	@Test
	public void createNewDeckReturnsValueFromManager() {
		String deckName = "deck1";
		Deck expectedDeck = new Deck(deckName);
		
		when(manager.create(deckName)).thenReturn(expectedDeck);
		
		Deck output = controller.createNewDeck(deckName);
		
		assertSame(expectedDeck, output);
	}
	
}
