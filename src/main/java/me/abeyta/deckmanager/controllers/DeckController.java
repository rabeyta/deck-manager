package me.abeyta.deckmanager.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.abeyta.deckmanager.delegates.DeckManager;
import me.abeyta.deckmanager.exceptions.DeckNotFoundException;
import me.abeyta.deckmanager.model.Deck;

@RestController
@RequestMapping("/decks")
public class DeckController {

	@Autowired
	private DeckManager manager;

	@RequestMapping(value="/{deckName}", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Deck createNewDeck(@PathVariable(value="deckName") String deckName) {
		return manager.create(new Deck(deckName));
	}
	
	@RequestMapping(value="/{deckName}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Deck getDeck(@PathVariable(value="deckName") String deckName) throws DeckNotFoundException {
		return manager.get(deckName);
	}
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Set<String> getDeckList() {
		return manager.getAllDeckNames();
	}
	
	@RequestMapping(value="/{deckName}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDeck(@PathVariable(value="deckName") String deckName) {
		manager.delete(deckName);
	}
	
	@RequestMapping(value="/{deckName}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Deck shuffleDeck(@PathVariable(value="deckName") String deckName) throws DeckNotFoundException {
		return manager.shuffle(deckName);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DeckNotFoundException.class)
	public void handleDeckNotFound() {}
}
