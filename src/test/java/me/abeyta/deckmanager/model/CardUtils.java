package me.abeyta.deckmanager.model;

import static me.abeyta.deckmanager.model.Suite.CLUBS;
import static me.abeyta.deckmanager.model.Suite.DIAMONDS;
import static me.abeyta.deckmanager.model.Suite.HEARTS;
import static me.abeyta.deckmanager.model.Suite.SPADES;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;

public class CardUtils {

	public static void assertAllCardsArePresent(Deck deck) {
		assertSuiteIsPresent(deck, HEARTS);
		assertSuiteIsPresent(deck, DIAMONDS);
		assertSuiteIsPresent(deck, CLUBS);
		assertSuiteIsPresent(deck, SPADES);
	}

	public static void assertSuiteIsPresent(Deck deck, Suite suite) {
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

	public static boolean isDeckInDefaultOrder(Deck deck) {
		Set<Card> defaultOrderedCards = createDefaultOrderedCardList();
		int defaultCardSize = defaultOrderedCards.size();
		Card[] defaultCards = defaultOrderedCards.toArray(new Card[defaultCardSize]);
		Card[] deckCards = deck.getCards().toArray(new Card[defaultCardSize]);
		for (int x = 0; x < 52; x++) {
			if (!defaultCards[x].equals(deckCards[x]))
				return false;
		}
		return true;
	}
	
	public static boolean isDeckSizeCorrect(Deck deck) {
		return deck.getCards().size() == 52;
	}

	public static Set<Card> createDefaultOrderedCardList() {
		Set<Card> defaultCards = new LinkedHashSet<>();
		defaultCards.add(new Card(HEARTS, "A"));
		defaultCards.add(new Card(HEARTS, "2"));
		defaultCards.add(new Card(HEARTS, "3"));
		defaultCards.add(new Card(HEARTS, "4"));
		defaultCards.add(new Card(HEARTS, "5"));
		defaultCards.add(new Card(HEARTS, "6"));
		defaultCards.add(new Card(HEARTS, "7"));
		defaultCards.add(new Card(HEARTS, "8"));
		defaultCards.add(new Card(HEARTS, "9"));
		defaultCards.add(new Card(HEARTS, "10"));
		defaultCards.add(new Card(HEARTS, "J"));
		defaultCards.add(new Card(HEARTS, "Q"));
		defaultCards.add(new Card(HEARTS, "K"));

		defaultCards.add(new Card(DIAMONDS, "A"));
		defaultCards.add(new Card(DIAMONDS, "2"));
		defaultCards.add(new Card(DIAMONDS, "3"));
		defaultCards.add(new Card(DIAMONDS, "4"));
		defaultCards.add(new Card(DIAMONDS, "5"));
		defaultCards.add(new Card(DIAMONDS, "6"));
		defaultCards.add(new Card(DIAMONDS, "7"));
		defaultCards.add(new Card(DIAMONDS, "8"));
		defaultCards.add(new Card(DIAMONDS, "9"));
		defaultCards.add(new Card(DIAMONDS, "10"));
		defaultCards.add(new Card(DIAMONDS, "J"));
		defaultCards.add(new Card(DIAMONDS, "Q"));
		defaultCards.add(new Card(DIAMONDS, "K"));

		defaultCards.add(new Card(CLUBS, "A"));
		defaultCards.add(new Card(CLUBS, "2"));
		defaultCards.add(new Card(CLUBS, "3"));
		defaultCards.add(new Card(CLUBS, "4"));
		defaultCards.add(new Card(CLUBS, "5"));
		defaultCards.add(new Card(CLUBS, "6"));
		defaultCards.add(new Card(CLUBS, "7"));
		defaultCards.add(new Card(CLUBS, "8"));
		defaultCards.add(new Card(CLUBS, "9"));
		defaultCards.add(new Card(CLUBS, "10"));
		defaultCards.add(new Card(CLUBS, "J"));
		defaultCards.add(new Card(CLUBS, "Q"));
		defaultCards.add(new Card(CLUBS, "K"));

		defaultCards.add(new Card(SPADES, "A"));
		defaultCards.add(new Card(SPADES, "2"));
		defaultCards.add(new Card(SPADES, "3"));
		defaultCards.add(new Card(SPADES, "4"));
		defaultCards.add(new Card(SPADES, "5"));
		defaultCards.add(new Card(SPADES, "6"));
		defaultCards.add(new Card(SPADES, "7"));
		defaultCards.add(new Card(SPADES, "8"));
		defaultCards.add(new Card(SPADES, "9"));
		defaultCards.add(new Card(SPADES, "10"));
		defaultCards.add(new Card(SPADES, "J"));
		defaultCards.add(new Card(SPADES, "Q"));
		defaultCards.add(new Card(SPADES, "K"));

		return defaultCards;
	}

}
