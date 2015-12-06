package me.abeyta.deckmanager.controllers;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import me.abeyta.deckmanager.delegates.DeckManager;

@RunWith(MockitoJUnitRunner.class)
public class DeckControllerTest {

	@InjectMocks
	private DeckController controller;
	
	@Mock
	private DeckManager manager;
	
	private String deckName = "deck1";
	
	@Test
	public void createNewDeck() {
		controller.createNewDeck(deckName);
		
		verify(manager).create(deckName);
	}
	
	@Test
	public void getDeck() {
		controller.getDeck(deckName);
		
		verify(manager).get(deckName);
	}
	
	@Test
	public void deleteDeck() {
		controller.deleteDeck(deckName);
		
		verify(manager).delete(deckName);
	}
	
	@Test
	public void getAllDeckNames() {
		Set<String> expected = Collections.singleton("deckName");
		when(manager.getAllDeckNames()).thenReturn(expected);
		
		Set<String> deckList = controller.getDeckList();
		
		assertSame(expected, deckList);
		verify(manager).getAllDeckNames();
	}
	
}
