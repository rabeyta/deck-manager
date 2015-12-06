package me.abeyta.deckmanager.model;

import static me.abeyta.deckmanager.model.CardUtils.assertAllCardsArePresent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DeckTest {
	
	@Test
	public void emptyConstructorCreatesDefaultDeck() {
		Deck deck = new Deck();
		
		assertEquals("default", deck.getName());
		assertTrue(deck.getCards().size() == 52);
		assertAllCardsArePresent(deck);
	}
	
	@Test
	public void namedConstructorCreatesDeck() {
		Deck deck = new Deck("robsdeck");
		
		assertEquals("robsdeck", deck.getName());
		assertTrue(deck.getCards().size() == 52);
		assertAllCardsArePresent(deck);
	}

}
