package me.abeyta.deckmanager.data.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import me.abeyta.deckmanager.data.DeckDao;
import me.abeyta.deckmanager.model.Deck;

@Component
public class InMemoryDeckDao implements DeckDao {

	private Map<String, Deck> decks = new HashMap<>();
	
	@Override
	public Deck createOrReplace(String deckName) {
		Deck deck = new Deck(deckName);
		decks.put(deckName, deck);
		return deck;
	}

}
