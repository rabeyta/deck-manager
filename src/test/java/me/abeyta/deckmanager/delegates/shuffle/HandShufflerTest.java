package me.abeyta.deckmanager.delegates.shuffle;

import static me.abeyta.deckmanager.model.CardUtils.isDeckInDefaultOrder;
import static me.abeyta.deckmanager.model.CardUtils.isDeckSizeCorrect;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import me.abeyta.deckmanager.model.Deck;

public class HandShufflerTest {

	private HandShuffler shuffler;
	
	@Before
	public void setup() {
		shuffler = new HandShuffler();
	}
	
	@Test
	public void shuffleChangesOrder() {
		Deck deck = new Deck();
		assertTrue("Deck was not in default order", isDeckInDefaultOrder(deck));
		
		shuffler.shuffle(deck);
		
		assertTrue("Deck was not right size", isDeckSizeCorrect(deck));
		assertFalse("Deck was not shuffled", isDeckInDefaultOrder(deck));
	}
}
