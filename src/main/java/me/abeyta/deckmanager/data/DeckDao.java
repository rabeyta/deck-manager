package me.abeyta.deckmanager.data;

import java.util.Set;

import me.abeyta.deckmanager.model.Deck;

public interface DeckDao {

	public Deck createOrReplace(String deckName);
	public Deck getDeckByName(String deckName);
	public void delete(String deckName);
	public Set<String> getAllExistingDeckNames();
}
