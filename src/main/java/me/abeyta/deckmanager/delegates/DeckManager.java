package me.abeyta.deckmanager.delegates;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import me.abeyta.deckmanager.data.DeckDao;
import me.abeyta.deckmanager.delegates.shuffle.Shuffler;
import me.abeyta.deckmanager.model.Deck;

@Component
@Primary
public class DeckManager {

	@Autowired
	private DeckDao dao;
	
	@Autowired
	private Shuffler shuffler;
	
	public Deck create(Deck deck) {
		return dao.createOrReplace(deck);
	}

	public Deck get(String deckName) {
		return dao.getDeckByName(deckName);
	}

	public void delete(String deckName) {
		dao.delete(deckName);
	}

	public Set<String> getAllDeckNames() {
		return dao.getAllExistingDeckNames();
	}
	
	public Deck shuffle(String deckName) {
		Deck deck = dao.getDeckByName(deckName);
		shuffler.shuffle(deck);
		dao.createOrReplace(deck);
		return deck;
	}

}
