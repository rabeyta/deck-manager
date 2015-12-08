package me.abeyta.deckmanager.delegates.shuffle;

import static me.abeyta.deckmanager.delegates.shuffle.ShufflerUtils.convertCardArrayIntoSet;
import static me.abeyta.deckmanager.delegates.shuffle.ShufflerUtils.convertDeckIntoCardArray;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

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

		Deck baseDeck = new Deck(deck.getName());
		do {
			Card[] shuffledCards = shuffleDeck(cards);
			deck.setCards(convertCardArrayIntoSet(shuffledCards));
		} while(baseDeck.equals(deck)); //ensure we never return a deck that is in original, unshuffled order
		//subsequent calls to this method could eventually cause the deck to end up in a starting state.
		//Shuffle again when that happens to ensure a shuffled deck is always returned.
	}

	private Card[] shuffleDeck(Card[] cards) {
		int numberOfShuffles = getRandomShuffleCountThatDoesntResetDeck();

		Card[] shuffledCards = shuffleCards(cards);
		for(int x = 1; x < numberOfShuffles; x++) {//start at 1 to account for full number of shuffles since we already did one.
			shuffledCards = shuffleCards(shuffledCards);
		}
		return shuffledCards;
	}

	private int getRandomShuffleCountThatDoesntResetDeck() {
		int numberOfShuffles = 0;
		
		do {
			numberOfShuffles = RandomUtils.nextInt(1, 100);
		} while(numberOfShuffles % 8 == 0); //ensure we do not reset the deck to the starting point with an 8
		
		return numberOfShuffles;
	}

	private Card[] shuffleCards(Card[] cards) {
		Pair<Card[], Card[]> cardHalves = splitCards(cards); 
		
		Card[] leftSide = cardHalves.getLeft();
		Card[] rightSide = cardHalves.getRight();

		List<Card> shuffledList = new ArrayList<>();
		for(int x = 0; x < leftSide.length; x ++) {
			shuffledList.add(leftSide[x]);
			shuffledList.add(rightSide[x]);
		}

		return shuffledList.toArray(new Card[shuffledList.size()]);
	}

	private Pair<Card[], Card[]> splitCards(Card[] cards) {
		int halfCount = cards.length / 2;
		
		Card[] bottom = (Card[]) ArrayUtils.subarray(cards, 0, halfCount);
		Card[] top = (Card[]) ArrayUtils.subarray(cards, halfCount, cards.length);

		return new ImmutablePair<>(bottom, top);
	}

}
