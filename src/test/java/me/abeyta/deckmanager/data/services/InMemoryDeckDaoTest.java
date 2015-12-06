package me.abeyta.deckmanager.data.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import me.abeyta.deckmanager.exceptions.DeckNotFoundException;
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
	public void createOrReplaceNotThereBeforeAddsDeck() throws DeckNotFoundException {
		Deck deck1 = dao.createOrReplace(deck);
		assertSame(deck, deck1);

		assertSame(deck1, dao.getDeckByName(deckName));
	}

	@Test
	public void createOrReplaceIsAlreadyThereReplacesDeck() throws DeckNotFoundException {
		Deck initialReturnedDeck = dao.createOrReplace(deck);
		assertSame(initialReturnedDeck, dao.getDeckByName(deckName));

		Deck newDeck1 = dao.createOrReplace(new Deck(deckName));
		assertNotSame(initialReturnedDeck, newDeck1);
	}

	@Test
	public void deleteDeckExistingRemoves() throws DeckNotFoundException {
		dao.createOrReplace(deck);

		Deck deck = dao.getDeckByName(deckName);
		assertNotNull(deck);

		dao.delete(deckName);
		try {
			dao.getDeckByName(deckName);
			fail("should have thrown exception for being gone");
		} catch (DeckNotFoundException e) {
			// Expected behavior
		}
	}

	@Test
	public void deleteNonExistingDeckNoErrorsThrown() throws DeckNotFoundException {
		try{
			dao.getDeckByName(deckName);
			fail("should have thrown DeckNotFoundException");
		} catch(DeckNotFoundException e) {
			//exception shoudl be thrown. proves its not existing
		}

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
