package me.abeyta.deckmanager.delegates.shuffle;

import static me.abeyta.deckmanager.delegates.shuffle.ShufflerUtils.convertCardArrayIntoSet;
import static me.abeyta.deckmanager.delegates.shuffle.ShufflerUtils.convertDeckIntoCardArray;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import me.abeyta.deckmanager.model.Card;
import me.abeyta.deckmanager.model.Deck;

public class HandShuffler implements Shuffler {

	/**
	 * Hand shuffling technique that weaves a half of a deck over each other
	 * 
	 * https://en.wikipedia.org/wiki/Faro_shuffle
	 */
	@Override
	public void shuffle(Deck deck) {
		Card[] cards = convertDeckIntoCardArray(deck);

		int numberOfShuffles = getRandomShuffleCountThatDoesntResetDeck();

		Card[] shuffledCards = shuffleCards(cards);
		for(int x = 0; x < numberOfShuffles; x++) {
			shuffledCards = shuffleCards(shuffledCards);
		}

		deck.setCards(convertCardArrayIntoSet(shuffledCards));
	}

	private int getRandomShuffleCountThatDoesntResetDeck() {
		int numberOfShuffles = 0;
		
		do {
			numberOfShuffles = RandomUtils.nextInt(1, 100) + 1; //account for previous shuffle already done
		} while(numberOfShuffles % 8 == 0 || numberOfShuffles % 52 == 0); //ensure we do not reset the deck with an 8 or 52
		
		return numberOfShuffles;
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
