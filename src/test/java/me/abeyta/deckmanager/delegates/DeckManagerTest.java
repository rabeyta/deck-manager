package me.abeyta.deckmanager.delegates;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import me.abeyta.deckmanager.data.DeckDao;
import me.abeyta.deckmanager.delegates.shuffle.Shuffler;
import me.abeyta.deckmanager.model.Deck;

@RunWith(MockitoJUnitRunner.class)
public class DeckManagerTest {

	@InjectMocks
	private DeckManager manager;
	@Mock
	private DeckDao dao;
	@Mock
	private Shuffler shuffler;
	
	private Deck deck;
	private String deckName;
	
	@Before
	public void setup() {
		deckName = "myDeck";
		deck = new Deck(deckName);
	}
	
	@Test
	public void createReturnsDeckFromDao() {
		when(dao.createOrReplace(deck)).thenReturn(deck);
		
		Deck output = manager.create(deck);
		
		assertSame(deck , output);
	}
	
	@Test
	public void getDeck() {
		when(dao.getDeckByName(deckName)).thenReturn(deck);
		Deck output = manager.get(deckName);
		
		assertSame(deck, output);
		verify(dao).getDeckByName(deckName);
	}
	
	@Test
	public void deleteDeck() {
		manager.delete(deckName);
		
		verify(dao).delete(deckName);
	}
	
	@Test
	public void getAllDeckNames() {
		Set<String> names = Collections.singleton("name");
		when(dao.getAllExistingDeckNames()).thenReturn(names);
		
		Set<String> output = manager.getAllDeckNames();
		
		assertSame(names, output);
		verify(dao).getAllExistingDeckNames();
	}
	
	@Test
	public void shuffle() {
		when(dao.getDeckByName(deckName)).thenReturn(deck);
		
		Deck output = manager.shuffle(deckName);
		
		verify(shuffler).shuffle(deck);
		assertSame(deck, output);
	}
}
