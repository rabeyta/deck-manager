package me.abeyta.deckmanager.data.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

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
	
	@Before
	public void setup() {
		deckName = "deck1";
	}
	
	@Test
	public void createOrReplaceNotThereBeforeAddsDeck() {
		Deck deck1 = dao.createOrReplace(deckName);
		
		assertSame(deck1, dao.getDeckByName(deckName));
	}
	
	@Test
	public void createOrReplaceIsAlreadyThereReplacesDeck() {
		Deck initialReturnedDeck = dao.createOrReplace(deckName);
		assertSame(initialReturnedDeck, dao.getDeckByName(deckName));
		
		Deck newDeck1 = dao.createOrReplace(deckName);
		assertNotSame(initialReturnedDeck, newDeck1);
	}
	
	@Test
	public void deleteDeckExistingRemoves() {
		dao.createOrReplace(deckName);
		
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
}
