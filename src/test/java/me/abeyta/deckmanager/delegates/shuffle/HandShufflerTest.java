package me.abeyta.deckmanager.delegates.shuffle;

import static me.abeyta.test.utils.CardUtils.isDeckInDefaultOrder;
import static me.abeyta.test.utils.CardUtils.validateDeck;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import me.abeyta.deckmanager.model.Deck;

public class HandShufflerTest {

	private HandShuffler shuffler;
	private Deck deck;

	@Before
	public void setup() {
		shuffler = new HandShuffler();
		deck = getDeck();
	}

	@Test
	public void shuffleChangesOrder() {
		shuffler.shuffle(deck);

		validateDeck(deck);
	}

	@Test
	public void shuffleALotToEnsureDeckIsNeverResetToOriginal() {
		for (int x = 0; x < 10000; x++) {
			shuffler.shuffle(deck);
			validateDeck(deck);
		}
	}

	private Deck getDeck() {
		Deck deck = new Deck();
		assertTrue("Deck was not in default order", isDeckInDefaultOrder(deck));
		return deck;
	}
}
