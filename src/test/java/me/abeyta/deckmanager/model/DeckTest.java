package me.abeyta.deckmanager.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

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

	private void assertAllCardsArePresent(Deck deck) {
		assertSuiteIsPresent(deck, Suite.HEARTS);
		assertSuiteIsPresent(deck, Suite.DIAMONDS);
		assertSuiteIsPresent(deck, Suite.CLUBS);
		assertSuiteIsPresent(deck, Suite.SPADES);
	}

	private void assertSuiteIsPresent(Deck deck, Suite suite) {
		Set<Card> cards = deck.getCards();
		assertTrue(cards.contains(new Card(suite, "A")));
		assertTrue(cards.contains(new Card(suite, "2")));
		assertTrue(cards.contains(new Card(suite, "3")));
		assertTrue(cards.contains(new Card(suite, "4")));
		assertTrue(cards.contains(new Card(suite, "5")));
		assertTrue(cards.contains(new Card(suite, "6")));
		assertTrue(cards.contains(new Card(suite, "7")));
		assertTrue(cards.contains(new Card(suite, "8")));
		assertTrue(cards.contains(new Card(suite, "9")));
		assertTrue(cards.contains(new Card(suite, "10")));
		assertTrue(cards.contains(new Card(suite, "J")));
		assertTrue(cards.contains(new Card(suite, "Q")));
		assertTrue(cards.contains(new Card(suite, "K")));
	}

}
