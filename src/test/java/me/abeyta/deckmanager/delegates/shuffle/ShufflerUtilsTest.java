package me.abeyta.deckmanager.delegates.shuffle;

import static me.abeyta.deckmanager.model.CardUtils.assertAllCardsArePresent;
import static me.abeyta.deckmanager.model.CardUtils.createCardDeck;
import static me.abeyta.deckmanager.model.CardUtils.isDeckInDefaultOrder;
import static me.abeyta.deckmanager.model.CardUtils.isDeckSizeCorrect;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import me.abeyta.deckmanager.model.Card;
import me.abeyta.deckmanager.model.Deck;

public class ShufflerUtilsTest {

	@Test
	public void constructor() { //code coverage..i like seeing 100%
		assertNotNull(new ShufflerUtils());
	}

	@Test
	public void convertDeckIntoArray() {
		Deck deck = new Deck();
		
		Card[] cards = ShufflerUtils.convertDeckIntoCardArray(deck);
		
		assertTrue("Size is not right", cards.length == 52);
	}
	
	@Test
	public void convertCardsIntoSet() {
		Set<Card> cardSet = ShufflerUtils.convertCardArrayIntoSet(createCardDeck());
		
		Deck deck = new Deck("test", cardSet);
		
		assertTrue("Deck was not right size", isDeckSizeCorrect(deck));
		assertTrue("Deck was not in same order as expected, default", isDeckInDefaultOrder(deck));
		assertAllCardsArePresent(deck);
	}
}
