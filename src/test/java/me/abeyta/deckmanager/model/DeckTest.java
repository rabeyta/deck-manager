package me.abeyta.deckmanager.model;

import static me.abeyta.test.utils.CardUtils.assertAllCardsArePresent;
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
	
	@Test
	public void defaultDecktoStringTesting() {
		assertEquals("Deck[name=default,cards=[Card[suite=HEARTS,faceValue=A], Card[suite=HEARTS,faceValue=2], Card[suite=HEARTS,faceValue=3], Card[suite=HEARTS,faceValue=4], Card[suite=HEARTS,faceValue=5], Card[suite=HEARTS,faceValue=6], Card[suite=HEARTS,faceValue=7], Card[suite=HEARTS,faceValue=8], Card[suite=HEARTS,faceValue=9], Card[suite=HEARTS,faceValue=10], Card[suite=HEARTS,faceValue=J], Card[suite=HEARTS,faceValue=Q], Card[suite=HEARTS,faceValue=K], Card[suite=DIAMONDS,faceValue=A], Card[suite=DIAMONDS,faceValue=2], Card[suite=DIAMONDS,faceValue=3], Card[suite=DIAMONDS,faceValue=4], Card[suite=DIAMONDS,faceValue=5], Card[suite=DIAMONDS,faceValue=6], Card[suite=DIAMONDS,faceValue=7], Card[suite=DIAMONDS,faceValue=8], Card[suite=DIAMONDS,faceValue=9], Card[suite=DIAMONDS,faceValue=10], Card[suite=DIAMONDS,faceValue=J], Card[suite=DIAMONDS,faceValue=Q], Card[suite=DIAMONDS,faceValue=K], Card[suite=CLUBS,faceValue=A], Card[suite=CLUBS,faceValue=2], Card[suite=CLUBS,faceValue=3], Card[suite=CLUBS,faceValue=4], Card[suite=CLUBS,faceValue=5], Card[suite=CLUBS,faceValue=6], Card[suite=CLUBS,faceValue=7], Card[suite=CLUBS,faceValue=8], Card[suite=CLUBS,faceValue=9], Card[suite=CLUBS,faceValue=10], Card[suite=CLUBS,faceValue=J], Card[suite=CLUBS,faceValue=Q], Card[suite=CLUBS,faceValue=K], Card[suite=SPADES,faceValue=A], Card[suite=SPADES,faceValue=2], Card[suite=SPADES,faceValue=3], Card[suite=SPADES,faceValue=4], Card[suite=SPADES,faceValue=5], Card[suite=SPADES,faceValue=6], Card[suite=SPADES,faceValue=7], Card[suite=SPADES,faceValue=8], Card[suite=SPADES,faceValue=9], Card[suite=SPADES,faceValue=10], Card[suite=SPADES,faceValue=J], Card[suite=SPADES,faceValue=Q], Card[suite=SPADES,faceValue=K]]]", new Deck().toString());
	}
}
