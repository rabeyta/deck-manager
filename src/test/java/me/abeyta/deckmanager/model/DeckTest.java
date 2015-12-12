package me.abeyta.deckmanager.model;

import static me.abeyta.deckmanager.model.CardUtils.assertAllCardsArePresent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import me.abeyta.deckmanager.delegates.shuffle.SimpleShuffler;

public class DeckTest {
	
	@Test
	public void emptyConstructorCreatesDefaultDeck() {
		Deck deck = new Deck();
		
		assertEquals("default", deck.getName());
		assertTrue(deck.getCards().size() == 52);
		assertAllCardsArePresent(deck);
	}
	
	@Test
	public void nameAndCardConstructor() {
		String name = "rob";
		Set<Card> cards = new HashSet<>();
		
		Deck deck = new Deck(name, cards);
		
		assertEquals(name, deck.getName());
		assertSame(cards, deck.getCards());
	}
	
	@Test
	public void namedConstructorCreatesDeck() {
		Deck deck = new Deck("robsdeck");
		
		assertEquals("robsdeck", deck.getName());
		assertTrue(deck.getCards().size() == 52);
		assertAllCardsArePresent(deck);
	}
	
	@Test
	public void setNameChangesIt() {
		Deck deck = new Deck();
		deck.setName("blah");
		
		assertEquals("blah", deck.getName());
	}
	
	@Test
	public void differentNamesNotEquals() {
		Deck deck1 = new Deck();
		Deck deck2 = new Deck("deck2");
		
		assertFalse(deck1.equals(deck2));
	}
	
	@Test
	public void differentCardOrderNotEquals() {
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		new SimpleShuffler().shuffle(deck2);
		
		assertFalse(deck1.equals(deck2));
	}
	
	@Test
	public void sameCardOrderEquals() {
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		
		assertTrue(deck1.equals(deck2));
	}
	
	@Test
	public void differentCardCountNotEqual() {
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		deck2.getCards().add(new Card());
		
		assertFalse(deck1.equals(deck2));
	}
	
	@Test
	public void nullNotEqual() {
		Deck deck1 = new Deck();
		assertFalse(deck1.equals(null));
	}
	
	@Test
	public void diffTypeNotEqual() {
		Deck deck1 = new Deck();
		assertFalse(deck1.equals(""));
	}

	@Test
	public void sameObjectEqual() {
		Deck deck1 = new Deck();
		assertTrue(deck1.equals(deck1));
	}
}
