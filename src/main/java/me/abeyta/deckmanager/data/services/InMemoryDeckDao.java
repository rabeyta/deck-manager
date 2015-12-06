package me.abeyta.deckmanager.data.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import me.abeyta.deckmanager.data.DeckDao;
import me.abeyta.deckmanager.model.Deck;

@Component
@Primary
public class InMemoryDeckDao implements DeckDao {

	private Map<String, Deck> decks = new HashMap<>();
	
	@Override
	public Deck createOrReplace(String deckName) {
		Deck deck = new Deck(deckName);
		decks.put(deckName, deck);
		return deck;
	}

	@Override
	public Deck getDeckByName(String deckName) {
		return decks.get(deckName);
	}

	@Override
	public void delete(String deckName) {
		decks.remove(deckName);
	}

	@Override
	public Set<String> getAllExistingDeckNames() {
		return decks.keySet();
	}

}
