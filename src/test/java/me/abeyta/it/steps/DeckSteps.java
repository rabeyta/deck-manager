package me.abeyta.it.steps;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import me.abeyta.deckmanager.model.Deck;

@Component
public class DeckSteps {

	@Autowired
	private RestTemplate rest;
	@Resource(name="decksBaseUrl")
	private URI decksUri;
	
	public void createNewDeck(String deckName) {
		rest.put(createDeckResourceUrl(deckName), null, new Object[] {});
	}
	
	public void deleteDeck(String deckName) {
		rest.delete(createDeckResourceUrl(deckName));
	}
	
	public Deck get(String deckName) {
		return rest.getForObject(createDeckResourceUrl(deckName), Deck.class);
	}

	private String createDeckResourceUrl(String deckName) {
		return decksUri.toString() + "/" + deckName;
	}

	public Deck shuffle(String deckName) {
		return rest.postForObject(createDeckResourceUrl(deckName), deckName, Deck.class);
	}

	@SuppressWarnings("unchecked")
	public List<String> getDecks() {
		return rest.getForObject(decksUri.toString(), List.class);
	}

}
