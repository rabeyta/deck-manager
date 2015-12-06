package me.abeyta.deckmanager.data.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import me.abeyta.deckmanager.data.DeckDao;
import me.abeyta.deckmanager.exceptions.DeckNotFoundException;
import me.abeyta.deckmanager.model.Deck;

@Component
@Primary
public class InMemoryDeckDao implements DeckDao {

	private Map<String, Deck> decks = new HashMap<>();
	
	@Override
	public Deck createOrReplace(Deck deck) {
		String deckName = deck.getName();
		
		delete(deckName);
		
		decks.put(deckName, deck);
		return deck;
	}

	@Override
	public Deck getDeckByName(String deckName) throws DeckNotFoundException {
		Deck deck = decks.get(deckName);
		
		if(deck == null) {
			throw new DeckNotFoundException();
		}
		
		return deck;
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
