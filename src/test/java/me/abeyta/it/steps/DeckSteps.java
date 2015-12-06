package me.abeyta.it.steps;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import me.abeyta.deckmanager.model.Deck;

@Component
public class DeckSteps {

	@Autowired
	private RestTemplate rest;
	@Resource(name="decksBaseUrl")
	private URI decksUri;
	
	public void createNewDeck(String deckName) {
		rest.put(createDeckResourceUrl(deckName), deckName, new Object[] {});
	}
	
	public void deleteDeck(String deckName) {
		rest.delete(createDeckResourceUrl(deckName));
	}
	
	public Deck get(String deckName) {
		ResponseEntity<String> response = rest.getForEntity(createDeckResourceUrl(deckName), String.class, new Object[] {});
		return new Gson().fromJson(response.getBody(), Deck.class);
	}

	private String createDeckResourceUrl(String deckName) {
		return decksUri.toString() + "/" + deckName;
	}

	public Deck shuffle(String deckName) {
		ResponseEntity<String> response = rest.postForEntity(createDeckResourceUrl(deckName), deckName, String.class, new Object[] {});
		return new Gson().fromJson(response.getBody(), Deck.class);
	}

	@SuppressWarnings("unchecked")
	public List<String> getDecks() {
		return rest.getForObject(decksUri.toString(), List.class);
	}

}
