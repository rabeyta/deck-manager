package me.abeyta.deckmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import me.abeyta.deckmanager.delegates.DeckManager;
import me.abeyta.deckmanager.model.Deck;

@RestController
@RequestMapping("/decks")
public class DeckController {

	@Autowired
	private DeckManager manager;
	
	@RequestMapping(value="/{deckName}", method=RequestMethod.PUT)
	public Deck createNewDeck(@PathVariable(value="deckName") String deckName) {
		return manager.create(deckName);
	}
	
}
