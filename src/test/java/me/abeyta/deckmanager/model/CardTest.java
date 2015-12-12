package me.abeyta.deckmanager.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CardTest {
	
	private Card card;
	
	@Before
	public void setup() {
		card = new Card(Suite.CLUBS, "K");
	}
	
	@Test
	public void suiteGetterGetterDefaultConstructor() {
		card = new Card();
		
		card.setFaceValue("Ace");
		card.setSuite(Suite.DIAMONDS);
		
		assertEquals("Ace", card.getFaceValue());
		assertEquals(Suite.DIAMONDS, card.getSuite());
	}

	@Test
	public void constructorGetsSets() {
		assertEquals("K", card.getFaceValue());
		assertEquals(Suite.CLUBS, card.getSuite());
	}
	
	@Test
	public void cardsAreEqualWhenSameSuiteValue() {
		Card card2 = new Card(Suite.CLUBS, "K");
		
		assertTrue("Cards should be equal", card.equals(card2));
	}
	
	@Test
	public void cardsAreNotEqualWhenDifValue() {
		Card card2 = new Card(Suite.CLUBS, "Q");
		
		assertFalse("Cards should not be equal with different face values", card.equals(card2));
	}
	@Test
	public void cardsAreNotEqualWhenDifSuiteValue() {
		Card card2 = new Card(Suite.SPADES, "Q");
		
		assertFalse("Cards should not be equal with different suite", card.equals(card2));
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(-1567740405, card.hashCode());
	}
	
	
	@Test
	public void toStringTest() {
		assertEquals("Card[suite=CLUBS,faceValue=K]", card.toString());
	}
}
