package me.abeyta.deckmanager.data;

import java.util.Set;

import me.abeyta.deckmanager.exceptions.DeckNotFoundException;
import me.abeyta.deckmanager.model.Deck;

public interface DeckDao {

	public Deck createOrReplace(Deck deck);
	public Deck getDeckByName(String deckName) throws DeckNotFoundException;
	public void delete(String deckName);
	public Set<String> getAllExistingDeckNames();
}
