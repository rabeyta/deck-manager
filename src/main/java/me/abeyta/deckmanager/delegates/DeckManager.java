package me.abeyta.deckmanager.delegates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.abeyta.deckmanager.data.DeckDao;
import me.abeyta.deckmanager.model.Deck;

@Component
public class DeckManager {

	@Autowired
	private DeckDao dao;
	
	public Deck create(String deckName) {
		return dao.createOrReplace(deckName);
	}

}
