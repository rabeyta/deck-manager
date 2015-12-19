package me.abeyta.test.utils;

import static me.abeyta.deckmanager.model.Suite.CLUBS;
import static me.abeyta.deckmanager.model.Suite.DIAMONDS;
import static me.abeyta.deckmanager.model.Suite.HEARTS;
import static me.abeyta.deckmanager.model.Suite.SPADES;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import me.abeyta.deckmanager.model.Card;
import me.abeyta.deckmanager.model.Deck;
import me.abeyta.deckmanager.model.Suite;

public class CardUtils {

	public static void assertAllCardsArePresent(Deck deck) {
		assertSuiteIsPresent(deck, HEARTS);
		assertSuiteIsPresent(deck, DIAMONDS);
		assertSuiteIsPresent(deck, CLUBS);
		assertSuiteIsPresent(deck, SPADES);
	}
	
	public static void assertSuiteIsPresent(Deck deck, Suite suite) {
		Set<Card> cards = deck.getCards();
		for(Card card : createSuite(suite)) {
			assertTrue(cards.contains(card));			
		}
	}
	
	public static Card[] createCardDeck() {
		Card[] cards = ArrayUtils.addAll(null, createSuite(Suite.HEARTS));
		cards = ArrayUtils.addAll(cards, createSuite(Suite.DIAMONDS));
		cards = ArrayUtils.addAll(cards, createSuite(Suite.CLUBS));
		cards = ArrayUtils.addAll(cards, createSuite(Suite.SPADES));
		return cards;
	}
	
	public static Card[] createSuite(Suite suite) {
		Card[] cards = new Card[13];
		cards[0] = new Card(suite, "A");
		cards[1] = new Card(suite, "2");
		cards[2] = new Card(suite, "3");
		cards[3] = new Card(suite, "4");
		cards[4] = new Card(suite, "5");
		cards[5] = new Card(suite, "6");
		cards[6] = new Card(suite, "7");
		cards[7] = new Card(suite, "8");
		cards[8] = new Card(suite, "9");
		cards[9] = new Card(suite, "10");
		cards[10] = new Card(suite, "J");
		cards[11] = new Card(suite, "Q");
		cards[12] = new Card(suite, "K");
		return cards;
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
	
	public static void validateDeck(Deck deck) {
		assertTrue("Deck was not right size", isDeckSizeCorrect(deck));
		assertFalse("Deck was not shuffled", isDeckInDefaultOrder(deck));
		assertAllCardsArePresent(deck);
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
