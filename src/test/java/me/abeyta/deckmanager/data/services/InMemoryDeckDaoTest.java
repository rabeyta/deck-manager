package me.abeyta.deckmanager.data.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import me.abeyta.deckmanager.model.Deck;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryDeckDaoTest {

	private InMemoryDeckDao dao;
	
	@Before
	public void setup() {
		dao = new InMemoryDeckDao();
	}
	
	@Test
	public void createOrReplaceNotThereBeforeAddsDeck() {
		String deckName = "deck1";
		
		Deck deck1 = dao.createOrReplace(deckName);
		
		assertSame(deck1, dao.getDeckByName(deckName));
	}
	
	@Test
	public void createOrReplaceIsAlreadyThereReplacesDeck() {
		String deckName = "deck1";
		
		Deck initialReturnedDeck = dao.createOrReplace(deckName);
		assertSame(initialReturnedDeck, dao.getDeckByName(deckName));
		
		Deck newDeck1 = dao.createOrReplace(deckName);
		assertNotSame(initialReturnedDeck, newDeck1);
	}
	
	@Test
	public void deleteDeckExistingRemoves() {
		String deckName = "deck2";
		
		dao.createOrReplace(deckName);
		
		Deck deck = dao.getDeckByName(deckName);
		assertNotNull(deck);
		
		dao.delete(deckName);
		assertNull(dao.getDeckByName(deckName));
	}
}
