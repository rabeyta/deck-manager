package me.abeyta.deckmanager.delegates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import me.abeyta.deckmanager.data.DeckDao;
import me.abeyta.deckmanager.model.Deck;

@Component
@Primary
public class DeckManager {

	@Autowired
	private DeckDao dao;
	
	public Deck create(String deckName) {
		return dao.createOrReplace(deckName);
	}

	public Deck get(String deckName) {
		return dao.getDeckByName(deckName);
	}

	public void delete(String deckName) {
		dao.delete(deckName);
	}

}
