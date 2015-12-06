package me.abeyta.deckmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.abeyta.deckmanager.delegates.DeckManager;
import me.abeyta.deckmanager.model.Deck;

@RestController
@RequestMapping("/decks")
public class DeckController {

	@Autowired
	private DeckManager manager;

	@RequestMapping(value="/{deckName}", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Deck createNewDeck(@PathVariable(value="deckName") String deckName) {
		return manager.create(deckName);
	}
	
	@RequestMapping(value="/{deckName}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Deck getDeck(@PathVariable(value="deckName") String deckName) {
		//TODO: 404 when not found
		return manager.get(deckName);
	}
	
	@RequestMapping(value="/{deckName}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDeck(@PathVariable(value="deckName") String deckName) {
		//TODO: 404 when not found
		manager.delete(deckName);
	}
}
