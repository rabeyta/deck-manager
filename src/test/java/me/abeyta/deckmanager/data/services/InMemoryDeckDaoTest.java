package me.abeyta.deckmanager.data.services;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import me.abeyta.deckmanager.model.Deck;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryDeckDaoTest {

	@InjectMocks
	private InMemoryDeckDao dao;
	private String deckName;
	private Deck deck;
	
	@Before
	public void setup() {
		deckName = "deck1";
		deck = new Deck(deckName);
	}
	
	@Test
	public void createOrReplaceNotThereBeforeAddsDeck() {
		Deck deck1 = dao.createOrReplace(deck);
		assertSame(deck, deck1);
		
		assertSame(deck1, dao.getDeckByName(deckName));
	}
	
	@Test
	public void createOrReplaceIsAlreadyThereReplacesDeck() {
		Deck initialReturnedDeck = dao.createOrReplace(deck);
		assertSame(initialReturnedDeck, dao.getDeckByName(deckName));
		
		Deck newDeck1 = dao.createOrReplace(new Deck(deckName));
		assertNotSame(initialReturnedDeck, newDeck1);
	}
	
	@Test
	public void deleteDeckExistingRemoves() {
		dao.createOrReplace(deck);
		
		Deck deck = dao.getDeckByName(deckName);
		assertNotNull(deck);
		
		dao.delete(deckName);
		assertNull(dao.getDeckByName(deckName));
	}
	
	@Test
	public void deleteNonExistingDeckNoErrorsThrown() {
		assertNull(dao.getDeckByName(deckName));
		
		dao.delete(deckName);
	}
	
	@Test
	public void getAllExistingNames() {
		dao.createOrReplace(new Deck("deck1"));
		dao.createOrReplace(new Deck("deck2"));
		
		Set<String> names = dao.getAllExistingDeckNames();
		
		assertEquals(2, names.size());
		assertTrue(names.contains("deck1"));
		assertTrue(names.contains("deck2"));
	}
}
