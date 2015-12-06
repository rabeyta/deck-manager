package me.abeyta.deckmanager.delegates.shuffle;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
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

	private Set<Card> convertCardArrayIntoSet(Card[] cards) {
		Set<Card> shuffledCardSet = new LinkedHashSet<>();
		CollectionUtils.addAll(shuffledCardSet, cards);
		return shuffledCardSet;
	}

	private Card[] convertDeckIntoCardArray(Deck deck) {
		Set<Card> cardSet = deck.getCards();
		return cardSet.toArray(new Card[cardSet.size()]);
	}

	private void shuffleCards(Card[] cards) {
		for(int x = cards.length - 1; x > 0; x--) {
			int randomNumber = RandomUtils.nextInt(0, x);
			Card holder = cards[x];
			cards[x] = cards[randomNumber];
			cards[randomNumber] = holder;
		}
	}

}
