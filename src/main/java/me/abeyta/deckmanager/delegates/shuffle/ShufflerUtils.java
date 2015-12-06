package me.abeyta.deckmanager.delegates.shuffle;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import me.abeyta.deckmanager.model.Card;
import me.abeyta.deckmanager.model.Deck;

public class ShufflerUtils {

	public static Set<Card> convertCardArrayIntoSet(Card[] cards) {
		Set<Card> shuffledCardSet = new LinkedHashSet<>();
		CollectionUtils.addAll(shuffledCardSet, cards);
		return shuffledCardSet;
	}

	public static Card[] convertDeckIntoCardArray(Deck deck) {
		Set<Card> cardSet = deck.getCards();
		return cardSet.toArray(new Card[cardSet.size()]);
	}
	
}
