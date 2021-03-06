package me.abeyta.deckmanager.delegates.shuffle;

import static me.abeyta.deckmanager.delegates.shuffle.ShufflerUtils.convertCardArrayIntoSet;
import static me.abeyta.deckmanager.delegates.shuffle.ShufflerUtils.convertDeckIntoCardArray;

import org.apache.commons.lang3.RandomUtils;

import me.abeyta.deckmanager.model.Card;
import me.abeyta.deckmanager.model.Deck;

public class SimpleShuffler implements Shuffler {

	@Override
	/**
	 * https://en.wikipedia.org/wiki/Fisher–Yates_shuffle
	 * 
	 * Shuffle array in place
	 */
	public void shuffle(Deck deck) {
		Card[] cards = convertDeckIntoCardArray(deck);
		
		shuffleCards(cards);
		
		deck.setCards(convertCardArrayIntoSet(cards));
	}

	private void shuffleCards(Card[] cards) {
		for(int x = cards.length - 1; x > 0; x--) {
			int randomNumber = RandomUtils.nextInt(0, x + 1); //Increase by 1 to include x as a potential
			Card holder = cards[x];
			cards[x] = cards[randomNumber];
			cards[randomNumber] = holder;
		}
	}

}
