package me.abeyta.deckmanager.delegates.shuffle;

import static me.abeyta.deckmanager.delegates.shuffle.ShufflerUtils.convertCardArrayIntoSet;
import static me.abeyta.deckmanager.delegates.shuffle.ShufflerUtils.convertDeckIntoCardArray;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import me.abeyta.deckmanager.model.Card;
import me.abeyta.deckmanager.model.Deck;

public class HandShuffler implements Shuffler {

	@Override
	public void shuffle(Deck deck) {
		Card[] cards = convertDeckIntoCardArray(deck);

		Card[] shuffledCards = shuffleCards(cards);
		
		for(int x = 0; x < RandomUtils.nextInt(1, 100); x++) {
			shuffledCards = shuffleCards(cards);
		}

		deck.setCards(convertCardArrayIntoSet(shuffledCards));
	}

	private Card[] shuffleCards(Card[] cards) {
		Card[] leftSide = splitCards(cards, true);
		Card[] rightSide = splitCards(cards, false);

		List<Card> shuffledList = new ArrayList<>();
		for(int x = 0; x < leftSide.length; x ++) {
			shuffledList.add(leftSide[x]);
			shuffledList.add(rightSide[x]);
		}

		return shuffledList.toArray(new Card[shuffledList.size()]);
	}

	private Card[] splitCards(Card[] cards, boolean topHalf) {
		int halfCount = cards.length / 2;
		Card[] cardHalf = new Card[halfCount];
		int cardIndex = 0;
		if (topHalf) {
			cardIndex = halfCount;
		}
		for (int x = 0; x < halfCount; x++, cardIndex++) {
			cardHalf[x] = cards[cardIndex];
		}
		return cardHalf;
	}

}
